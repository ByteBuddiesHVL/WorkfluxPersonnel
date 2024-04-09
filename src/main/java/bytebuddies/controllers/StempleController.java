package bytebuddies.controllers;

import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Tidsplan;
import bytebuddies.entities.Tidsplantype;
import bytebuddies.services.AnsattService;
import bytebuddies.services.TidsplanService;
import bytebuddies.services.TidsplantypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Kontrollerklasse som håndterer stemplingsfunksjonalitet.
 */
@Controller
public class StempleController {

    @Autowired
    AnsattService ansattService;

    @Autowired
    TidsplanService tidsplanService;

    @Autowired
    TidsplantypeService tidsplantypeService;

    /**
     * Viser siden for stempling.
     *
     * @return Navnet på HTML-siden for stempling.
     */
    @GetMapping("/")
    public String startStempling() {
        return "stemple";
    }

    /**
     * Henter stemplingsinformasjon for en bestemt ansatt.
     *
     * @param brukernavn          Brukernavnet til den ansatte.
     * @param redirectAttributes Attributter for omdirigering.
     * @return Omdirigerer tilbake til stemplingssiden med informasjon om brukeren.
     */
    @GetMapping("/hentstempling")
    public String hentStempling(
            @RequestParam(name = "brukernavn") String brukernavn,
            RedirectAttributes redirectAttributes
    ) {
        String errorMessage = validerBruker(brukernavn);

        if (errorMessage != null) {
            redirectAttributes.addFlashAttribute("popup",false);
            redirectAttributes.addFlashAttribute("error",errorMessage);
        }
        else {
            redirectAttributes.addFlashAttribute("brukernavn",brukernavn);
            redirectAttributes.addFlashAttribute("popup",true);
            redirectAttributes.addFlashAttribute("time", LocalDateTime.now());
        }
        return "redirect:/";
    }

    /**
     * Sender stemplingsinformasjon for en ansatt.
     *
     * @param time       Tidspunktet for stemplingen.
     * @param brukernavn Brukernavnet til den ansatte.
     * @param type       Typen av stempling (Inn, Ut, Lunsj).
     * @return Omdirigerer tilbake til stemplingssiden.
     */
    @PostMapping("/sendstempling")
    public String sendStempling(
            @RequestParam(name = "time") LocalDateTime time,
            @RequestParam(name = "brukernavn") String brukernavn,
            @RequestParam(name = "type") String type
    ) {

        Ansatt ansatt = ansattService.getAnsattByBrukernavn(brukernavn);
        Tidsplan tidsplan = tidsplanService.getCurrentTidsplan(ansatt);
        Tidsplantype typeSalg = tidsplantypeService.getTidsplantypeById(1);
        Tidsplantype typeKasse = tidsplantypeService.getTidsplantypeById(2);
        Tidsplantype typeLunsj = tidsplantypeService.getTidsplantypeById(3);

        switch(type) {
            case "Inn":
                if (tidsplan != null) {
                    tidsplanService.endTidsplan(tidsplan, time.withSecond(0).withNano(0));

                    if (Objects.equals(tidsplan.getTypeId(), typeLunsj))
                        tidsplanService.saveTidsplan(new Tidsplan(ansatt, time.withSecond(0).withNano(0), null, typeSalg, false));
                } else
                    tidsplanService.saveTidsplan(new Tidsplan(ansatt,time.withSecond(0).withNano(0),null,typeSalg,false));
                break;
            case "Ut":
                if (tidsplan == null) break;
                LocalDate startDate = tidsplan.getStarttid().toLocalDate();
                LocalDate dateNow = time.toLocalDate();
                if (startDate.isEqual(dateNow))
                    tidsplanService.endTidsplan(tidsplan,time.withSecond(0).withNano(0));
                else if (startDate.isBefore(dateNow)){
                    tidsplanService.endTidsplan(tidsplan,startDate.atTime(23,59,0));
                    tidsplanService.saveTidsplan(new Tidsplan(ansatt,dateNow.atTime(0,0,0),time,tidsplan.getTypeId(),false));
                } else if (startDate.isAfter(dateNow)){
                    // return error
                }
                break;
            case "Lunsj":
                if (tidsplan != null) tidsplanService.endTidsplan(tidsplan,time.withSecond(0).withNano(0));
                tidsplanService.saveTidsplan(new Tidsplan(ansatt,time.withSecond(0).withNano(0),null,typeLunsj,false));
        }

        return "redirect:/";
    }

    /**
     * Validerer brukernavnet til en ansatt.
     *
     * @param brukernavn Brukernavnet som skal valideres.
     * @return En feilmelding hvis brukernavnet ikke er gyldig, ellers null.
     */
    public String validerBruker(String brukernavn) {
        if (!Pattern.compile("([a-z]{2})(\\d{6})").matcher(brukernavn).find()) return "Ikke et gyldig brukernavn.";
        if (ansattService.getAnsattByBrukernavn(brukernavn) == null) return "Finner ingen ansatt på dette brukernavnet";

        return null;
    }
}