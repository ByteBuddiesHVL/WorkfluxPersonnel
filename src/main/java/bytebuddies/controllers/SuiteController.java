package bytebuddies.controllers;

import bytebuddies.embeddable.Passord;
import bytebuddies.entities.*;
import bytebuddies.services.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Kontrollerklasse som håndterer funksjonalitet for administrasjonssuiten.
 */
@Controller
public class SuiteController {

    @Autowired
    AdminService adminService;

    @Autowired
    PassordService passordService;

    @Autowired
    AnsattService ansattService;

    @Autowired
    AdresseService adresseService;

    @Autowired
    BedriftService bedriftService;

    @Autowired
    PostnummerService postnummerService;

    @Autowired
    ValideringsService valServ;

    @Autowired
    StillingstypeService stillingstypeService;

    @Autowired
    TidsplanService tidsplanService;

    @Autowired
    TidsplantypeService tidsplantypeService;

    @Autowired
    LonnService lonnService;

    @Autowired
    SlippHistorikkService slippHistorikkService;

    private LocalDate currentDate = LocalDate.now();

    /**
     * Setter den valgte datoen for tidsplanen.
     *
     * @param year  Året.
     * @param month Måneden.
     * @param day   Dagen.
     * @return Omdirigerer til kalendersiden.
     */
    @GetMapping("/setDagTidsplan")
    public String setDagForTidsplan(
            @RequestParam("year") Integer year,
            @RequestParam("month") Integer month,
            @RequestParam("day") Integer day
    ) {
        if (year != null && month != null && day != null) {
            currentDate = LocalDate.of(year, month, day);
        }
        return "redirect:/suite/kalender";
    }

    /**
     * Endrer en tidsplan for en ansatt.
     *
     * @param tidsplanId  ID-en til tidsplanen.
     * @param date        Datoen for tidsplanen.
     * @param starttid    Starttidspunktet for tidsplanen.
     * @param sluttid     Sluttidspunktet for tidsplanen.
     * @param typeId      ID-en til tidsplantypen.
     * @return En streng som representerer tidsplanen.
     */
    @PostMapping(value = "/endreTime", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public String endreTime(
            @RequestParam("tidsplanId") Integer tidsplanId,
            @RequestParam("date") LocalDate date,
            @RequestParam("starttid") LocalTime starttid,
            @RequestParam("sluttid") LocalTime sluttid,
            @RequestParam("tidsplantype") Integer typeId
    ) {
        Tidsplan tidsplan = tidsplanService.getTidsplan(tidsplanId);
        Tidsplantype type = tidsplantypeService.getTidsplantypeById(typeId);

        tidsplan.setStarttid(starttid.atDate(date).withSecond(0).withNano(0));
        tidsplan.setSluttid(sluttid.atDate(date).withSecond(0).withNano(0));
        tidsplan.setTypeId(type);
        tidsplanService.saveTidsplan(tidsplan);

        return getTidsplanString(date);
    }

    /**
     * Endrer en tidsplan for en ansatt.
     *
     * @param date          Datoen for tidsplanen.
     * @param brukernavn    Brukernavnet til den ansatte.
     * @param starttid      Starttidspunktet for tidsplanen.
     * @param sluttid       Sluttidspunktet for tidsplanen.
     * @param typeId        ID-en til tidsplantypen.
     * @return En streng som representerer tidsplanen.
     */
    @PostMapping(value = "/timeAnsatt", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public String endreTime(
            @RequestParam("date") LocalDate date,
            @RequestParam("brukernavn") String brukernavn,
            @RequestParam("starttid") LocalTime starttid,
            @RequestParam("sluttid") LocalTime sluttid,
            @RequestParam("tidsplantype") Integer typeId
    ) {
        Ansatt ansatt = ansattService.getAnsattByBrukernavn(brukernavn);
        if (ansatt != null) {
            //todo - check if there is already a tidsplan between the starttid and sluttid
            Tidsplantype type = tidsplantypeService.getTidsplantypeById(typeId);
            Tidsplan tidsplan = new Tidsplan(ansatt,starttid.atDate(date).withSecond(0).withNano(0),sluttid.atDate(date).withSecond(0).withNano(0),type,false);
            tidsplanService.saveTidsplan(tidsplan);
        }
        return getTidsplanString(date);
    }

    /**
     * Genererer lønnsslipper.
     *
     * @param yearmonth     År og måned.
     * @param type          Type av lønnsslipper (all eller one).
     * @param brukernavn    Brukernavnet til den ansatte (hvis type er one).
     * @param session       HttpSession-objektet.
     * @return Omdirigerer til personaloversikten.
     */
    @PostMapping("/genererLonnsslipper")
    public String genererLonnsslipper(
            @RequestParam("month") String yearmonth,
            @RequestParam("for") String type,
            @RequestParam("brukernavn") String brukernavn,
            HttpSession session
    ) {
        int year = Integer.parseInt(yearmonth.split("-")[0]);
        int month = Integer.parseInt(yearmonth.split("-")[1]);
        LocalDate startDate = LocalDate.of(year,month,1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        Admin admin = getLoggedInAttr(session);

        if (admin != null) {
            Bedrift bedrift = admin.getBedriftId();
            LocalDate payDate = LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1);

            if (type.equals("all")) {
                lonnService.genererLonnsslippForAlleAnsatte(bedrift,startDate,endDate,payDate);

            } else if (type.equals("one")) {
                Ansatt ansatt = ansattService.getAnsattByBrukernavn(brukernavn);
                if (ansatt != null) {
                    try {
                        lonnService.genererLonnsslippForAnsatt(ansatt,startDate,endDate,payDate);
                    } catch (IOException e) {
                        // TODO return error
                    }
                }
            }
        }
        return "redirect:/suite/personal";
    }

    /**
     * Henter en lønnsslipp for en ansatt.
     *
     * @param filNavn       Filnavnet til lønnsslippen.
     * @param response      HttpServletResponse-objektet.
     * @return Omdirigerer til personaloversikten.
     */
    @GetMapping("/getLonnsslipp")
    public String hentLonnsslipp(
            @RequestParam("filnavn") String filNavn,
            HttpServletResponse response
    ) {
        String[] navn = filNavn.split("_");
        String[] dateS = navn[0].split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(dateS[0]),Integer.parseInt(dateS[1]),Integer.parseInt(dateS[2]));
        String brukernavn = navn[1];
        Ansatt ansatt = ansattService.getAnsattByBrukernavn(brukernavn);
        if (ansatt != null) {
            try {
                slippHistorikkService.convertToPDF(slippHistorikkService.hentSlipp(ansatt,date).getFileData(),response,filNavn + ".pdf");
            } catch (IOException e) {
                //TODO return error
            }
        }

        return "redirect:/suite/personal";
    }

    /**
     * Viser startsiden for administrasjonssuiten.
     *
     * @param session HttpSession-objektet.
     * @return Suite-siden hvis brukeren er logget inn, ellers logon-siden.
     */

    @GetMapping("/suite")
    public String getSuiteSite(HttpSession session) {
        Admin admin = getLoggedInAttr(session);
        if (admin == null) return "suite-logon";
        return "suite";
    }

    /**
     * Viser en delside av administrasjonssuiten.
     *
     * @param model         Model-objektet.
     * @param session       HttpSession-objektet.
     * @param delside       Deliden til administrasjonssuiten.
     * @param attributes    RedirectAttributes-objektet.
     * @return Suite-siden med den valgte delsiden.
     */
    @GetMapping("/suite/{delside}")
    public String getSuiteDelide(
            Model model,
            HttpSession session,
            @PathVariable("delside") String delside,
            RedirectAttributes attributes
    ) {
        Admin admin = getLoggedInAttr(session);
        if (admin == null) return "redirect:/suite";
        if (delside != null) {
            attributes.addFlashAttribute("delside", delside);
            switch (delside) {
                case "personal" -> {
                    model.addAttribute("ansatte", getAnsattString());
                    model.addAttribute("ansattListe", ansattService.getAllAnsatte());
                    model.addAttribute("stillingstyper", stillingstypeService.getAlleTyper(admin.getBedriftId()));
                }
                case "ansatt" -> {
                    model.addAttribute("ansatte", getAnsattString());
                    model.addAttribute("stillingstyper", stillingstypeService.getAlleTyper(admin.getBedriftId()));
                }
                case "kalender" -> {
                    model.addAttribute("ansatte", getAnsattString());
                    model.addAttribute("ansattListe", ansattService.getAllAnsatte());
                    model.addAttribute("dag", currentDate.toString());
                    model.addAttribute("tidsplan", getTidsplanString(currentDate));
                    model.addAttribute("tidsplantyper", tidsplantypeService.getTidsplantyperByBedrift(admin.getBedriftId()));
                }
            }
        }
        return "suite";
    }

    /**
     * Logger ut brukeren.
     *
     * @param session HttpSession-objektet.
     * @return Omdirigerer til startsiden for administrasjonssuiten.
     */
    @GetMapping("/logout")
    public String logOut(HttpSession session){
        session.invalidate();
        return "redirect:/suite";
    }

    /**
     * Logger inn brukeren.
     *
     * @param brukernavn    Brukernavnet til brukeren.
     * @param passord       Passordet til brukeren.
     * @param attributes    RedirectAttributes-objektet.
     * @param session       HttpSession-objektet.
     * @return Omdirigerer til startsiden for administrasjonssuiten.
     */
    @PostMapping("/login")
    public String logIn(
            @RequestParam(name = "brukernavn") String brukernavn,
            @RequestParam(name = "passord") String passord,
            RedirectAttributes attributes,
            HttpSession session
    ) {
        String errorMessage = valServ.validerAdmin(brukernavn,passord,session);
        if (errorMessage != null) attributes.addFlashAttribute("error",errorMessage);
        return "redirect:/suite";
    }

    /**
     * Oppretter en ny ansatt.
     *
     * @param fornavn           Fornavnet til den ansatte.
     * @param etternavn         Etternavnet til den ansatte.
     * @param telefonnummer     Telefonnummeret til den ansatte.
     * @param epost             E-postadressen til den ansatte.
     * @param gatenavn          Gatenavnet til den ansatte.
     * @param gatenummer        Gatenummeret til den ansatte.
     * @param postnummer        Postnummeret til den ansatte.
     * @param timelonn          Timelønnen til den ansatte.
     * @param stillingsprosent  Stillingsprosenten til den ansatte.
     * @param stillingstype     ID-en til stillingstypen.
     * @param session           HttpSession-objektet.
     * @param attributes        RedirectAttributes-objektet.
     * @return Omdirigerer til personalsiden i Suiten.
     */
    @PostMapping("/nyAnsatt")
    public String nyAnsatt(
            @RequestParam("fornavn") String fornavn,
            @RequestParam("etternavn") String etternavn,
            @RequestParam("telefonnummer") String telefonnummer,
            @RequestParam("epost") String epost,
            @RequestParam("gatenavn") String gatenavn,
            @RequestParam("gatenummer") String gatenummer,
            @RequestParam("postnummer") String postnummer,
            @RequestParam("timelonn") Float timelonn,
            @RequestParam("stillingsprosent") Float stillingsprosent,
            @RequestParam("stillingstype") Integer stillingstype,
            HttpSession session,
            RedirectAttributes attributes
    ) {
        String errorMessage = valServ.validerAnsatt(fornavn,etternavn,telefonnummer,epost,gatenavn,gatenummer,postnummer,timelonn,stillingsprosent,stillingstype);
        if (errorMessage != null) attributes.addFlashAttribute("error", errorMessage);
        else {
            Admin admin = getLoggedInAttr(session);
            if (admin == null) {
                attributes.addFlashAttribute("error", "Du er ikke logget inn!");
                return "redirect:/suite";
            }
            Bedrift bedrift = admin.getBedriftId();
            Ansatt ansatt = valServ.lagAnsatt(bedrift,fornavn,etternavn,telefonnummer,epost,gatenavn,gatenummer,postnummer,timelonn,stillingsprosent,stillingstype,etternavn);
            ansattService.saveAnsatt(ansatt,bedrift.getForkortelse());
            lonnService.lagreLonn(timelonn,null);
        }
        return "redirect:/suite/personal";
    }

    /**
     * Redigerer en eksisterende ansatt.
     *
     * @param brukernavn        Brukernavnet til den ansatte som skal redigeres.
     * @param fornavn           Det nye fornavnet til den ansatte.
     * @param etternavn         Det nye etternavnet til den ansatte.
     * @param telefonnummer     Det nye telefonnummeret til den ansatte.
     * @param epost             Den nye e-postadressen til den ansatte.
     * @param gatenavn          Det nye gatenavnet til den ansatte.
     * @param gatenummer        Det nye gatenummeret til den ansatte.
     * @param postnummer        Det nye postnummeret til den ansatte.
     * @param timelonn          Den nye timelønnen til den ansatte.
     * @param stillingsprosent  Den nye stillingsprosenten til den ansatte.
     * @param stillingstypeId   Den nye ID-en til stillingstypen.
     * @param slettAnsatt       Angir om ansatte skal slettes.
     * @param session           HttpSession-objektet.
     * @param attributes        RedirectAttributes-objektet.
     * @return Omdirigerer til personalsiden i Suiten hvis ansatte er slettet, ellers omdirigerer til ansatt-siden i Suiten.
     */
    @PostMapping("/redigerAnsatt")
    public String redigerAnsatt(
            @RequestParam("brukernavn") String brukernavn,
            @RequestParam("fornavn") String fornavn,
            @RequestParam("etternavn") String etternavn,
            @RequestParam("telefonnummer") String telefonnummer,
            @RequestParam("epost") String epost,
            @RequestParam("gatenavn") String gatenavn,
            @RequestParam("gatenummer") String gatenummer,
            @RequestParam("postnummer") String postnummer,
            @RequestParam("timelonn") Float timelonn,
            @RequestParam("stillingsprosent") Float stillingsprosent,
            @RequestParam("stillingstype") Integer stillingstypeId,
            @RequestParam(required = false, defaultValue = "false") boolean slettAnsatt,
            HttpSession session,
            RedirectAttributes attributes
    ) {

        if (slettAnsatt) {
            ansattService.deleteAnsattByBrukernavn(brukernavn);
            return "redirect:/suite/ansatt";
        }
        String errorMessage = valServ.validerAnsatt(fornavn,etternavn,telefonnummer,epost,gatenavn,gatenummer,postnummer,timelonn,stillingsprosent,stillingstypeId);
        if (errorMessage != null) attributes.addFlashAttribute("error", errorMessage);
        else {
            Admin admin = getLoggedInAttr(session);
            if (admin == null) {
                attributes.addFlashAttribute("error", "Du er ikke logget inn!");
                return "redirect:/suite";
            }
            Bedrift bedrift = admin.getBedriftId();
            Ansatt ansatt = ansattService.getAnsattByBrukernavn(brukernavn);
            Stillingstype stillingstype = stillingstypeService.getStillingstype(stillingstypeId);

            ansatt.setFornavn(fornavn);
            ansatt.setEtternavn(etternavn);
            ansatt.setTelefonnummer(telefonnummer);
            ansatt.setEpost(epost);
            ansatt.getAdresseId().setGatenavn(gatenavn);
            ansatt.getAdresseId().setGatenummer(gatenummer);
            ansatt.getAdresseId().getPostnummer().setPostnummer(postnummer);
            ansatt.setStillingsprosent(stillingsprosent);
            ansatt.setStillingstypeId(stillingstype);
            ansatt.setLonnId(lonnService.lagreLonn(timelonn,null));
            ansattService.saveAnsatt(ansatt,bedrift.getForkortelse());

        }
        return "redirect:/suite/ansatt";
    }

    /**
     * Henter timeplanen for en spesifikk dato.
     *
     * @param dag Dagen for hvilken timeplanen blir forespurt.
     * @return JSON-representasjon av timeplanen for den spesifiserte datoen.
     */
    @GetMapping(value = "/tidsplan", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public String hentTidsplan(
            @RequestParam(name = "dag", required = false) LocalDate dag
    ) {
        // TODO beskytt med admin
        return getTidsplanString(dag);
    }

    /**
     * Henter pålogget admin fra økten.
     *
     * @param session HttpSession-objektet.
     * @return Den påloggede adminen, eller null hvis ingen admin er pålogget.
     */
    public Admin getLoggedInAttr(HttpSession session) {
        return (Admin) session.getAttribute("loggedin");
    }

    /**
     * Henter en strengrepresentasjon av alle ansatte.
     *
     * @return En JSON-streng som representerer alle ansatte.
     */
    public String getAnsattString() {
        return ansattService.getAllAnsatte().stream()
                .map(Ansatt::toString)
                .collect(Collectors.joining(",", "[", "]"));
    }

    /**
     * Henter en strengrepresentasjon av timeplanen for en spesifikk dato.
     *
     * @param dato Datoen for hvilken timeplanen blir forespurt.
     * @return En JSON-streng som representerer timeplanen for den spesifiserte datoen.
     */
    public String getTidsplanString(LocalDate dato) {
        if (dato == null) dato = currentDate;
        return tidsplanService.getTidsplanByDate(dato).stream()
                .map(Tidsplan::toString)
                .collect(Collectors.joining(",", "[", "]"));
    }
}
