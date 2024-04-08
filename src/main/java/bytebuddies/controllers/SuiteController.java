package bytebuddies.controllers;

import bytebuddies.embeddable.Passord;
import bytebuddies.entities.*;
import bytebuddies.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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

    private LocalDate currentDate = LocalDate.now();

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

        tidsplan.setStarttid(starttid.atDate(date).withSecond(0));
        tidsplan.setSluttid(sluttid.atDate(date).withSecond(0));
        tidsplan.setTypeId(type);
        tidsplanService.saveTidsplan(tidsplan);

        return getTidsplanString(date);
    }

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
            Tidsplan tidsplan = new Tidsplan(ansatt,starttid.atDate(date).withSecond(0),sluttid.atDate(date).withSecond(0),type,false);
            tidsplanService.saveTidsplan(tidsplan);
        }
        return getTidsplanString(date);
    }

    @GetMapping("/suite")
    public String getSuiteSite(HttpSession session) {
        Admin admin = getLoggedInAttr(session);
        if (admin == null) return "suite-logon";
        return "suite";
    }

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
            if (delside.equals("personal")) {
                model.addAttribute("ansatte", getAnsattString());
                model.addAttribute("stillingstyper", stillingstypeService.getAlleTyper(admin.getBedriftId()));
            }
            else if (delside.equals("ansatt")) {
                model.addAttribute("ansatte", getAnsattString());
                model.addAttribute("stillingstyper", stillingstypeService.getAlleTyper(admin.getBedriftId()));
            }
            else if (delside.equals("kalender")) {
                model.addAttribute("ansatte", getAnsattString());
                model.addAttribute("ansattListe", ansattService.getAllAnsatte());
                model.addAttribute("dag", currentDate.toString());
                model.addAttribute("tidsplan", getTidsplanString(currentDate));
                model.addAttribute("tidsplantyper", tidsplantypeService.getTidsplantyperByBedrift(admin.getBedriftId()));
            }
        }
        return "suite";
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session){
        session.invalidate();
        return "redirect:/suite";
    }

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

    @PostMapping("/nyAnsatt")
    public String nyAnsatt(
            @RequestParam("fornavn") String fornavn,
            @RequestParam("etternavn") String etternavn,
            @RequestParam("telefonnummer") String telefonnummer,
            @RequestParam("epost") String epost,
            @RequestParam("gatenavn") String gatenavn,
            @RequestParam("gatenummer") String gatenummer,
            @RequestParam("postnummer") String postnummer,
            @RequestParam("stillingsprosent") Float stillingsprosent,
            @RequestParam("stillingstype") Integer stillingstype,
            HttpSession session,
            RedirectAttributes attributes
    ) {
        String errorMessage = valServ.validerAnsatt(fornavn,etternavn,telefonnummer,epost,gatenavn,gatenummer,postnummer,stillingsprosent,stillingstype);
        if (errorMessage != null) attributes.addFlashAttribute("error", errorMessage);
        else {
            Admin admin = getLoggedInAttr(session);
            if (admin == null) {
                attributes.addFlashAttribute("error", "Du er ikke logget inn!");
                return "redirect:/suite";
            }
            Bedrift bedrift = admin.getBedriftId();
            Ansatt ansatt = valServ.lagAnsatt(bedrift,fornavn,etternavn,telefonnummer,epost,gatenavn,gatenummer,postnummer,stillingsprosent,stillingstype,etternavn);
            ansattService.saveAnsatt(ansatt,bedrift.getForkortelse());
        }
        return "redirect:/suite/personal";
    }

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
        String errorMessage = valServ.validerAnsatt(fornavn,etternavn,telefonnummer,epost,gatenavn,gatenummer,postnummer,stillingsprosent,stillingstypeId);
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
            ansattService.saveAnsatt(ansatt,bedrift.getForkortelse());
        }
        return "redirect:/suite/ansatt";
    }

    @GetMapping(value = "/tidsplan", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public String hentTidsplan(
            @RequestParam(name = "dag", required = false) LocalDate dag
    ) {
        // TODO beskytt med admin
        return getTidsplanString(dag);
    }

    public Admin getLoggedInAttr(HttpSession session) {
        return (Admin) session.getAttribute("loggedin");
    }

    public String getAnsattString() {
        return ansattService.getAllAnsatte().stream()
                .map(Ansatt::toString)
                .collect(Collectors.joining(",", "[", "]"));
    }

    public String getTidsplanString(LocalDate dato) {
        if (dato == null) dato = currentDate;
        return tidsplanService.getTidsplanByDate(dato).stream()
                .map(Tidsplan::toString)
                .collect(Collectors.joining(",", "[", "]"));
    }
}
