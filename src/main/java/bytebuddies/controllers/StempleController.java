package bytebuddies.controllers;

import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Tidsplan;
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
import java.util.regex.Pattern;

@Controller
public class StempleController {

    @Autowired
    AnsattService ansattService;

    @Autowired
    TidsplanService tidsplanService;

    @Autowired
    TidsplantypeService tidsplantypeService;

    @GetMapping("/")
    public String startStempling() {
        return "stemple";
    }

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

    @PostMapping("/sendstempling")
    public String sendStempling(
            @RequestParam(name = "time") LocalDateTime time,
            @RequestParam(name = "brukernavn") String brukernavn,
            @RequestParam(name = "type") String type
    ) {

        Ansatt ansatt = ansattService.getAnsattByBrukernavn(brukernavn);
        Tidsplan tidsplan = tidsplanService.getCurrentTidsplan(ansatt);

        switch(type) {
            case "Inn":
                // check if tidsplan == lunsj ... etc
                if (tidsplan != null) tidsplanService.endTidsplan(tidsplan,time);
                tidsplanService.saveTidsplan(new Tidsplan(ansatt,time,null,null,false));
                break;
            case "Ut":
                if (tidsplan == null) break;
                LocalDate startDate = tidsplan.getStarttid().toLocalDate();
                LocalDate dateNow = time.toLocalDate();
                if (startDate.isEqual(dateNow))
                    tidsplanService.endTidsplan(tidsplan,time);
                else if (startDate.isBefore(dateNow)){
                    tidsplanService.endTidsplan(tidsplan,startDate.atTime(23,59,59));
                    tidsplanService.saveTidsplan(new Tidsplan(ansatt,dateNow.atTime(0,0,0),time,null,false));
                } else if (startDate.isAfter(dateNow)){
                    // return error
                }
                break;
            case "Lunsj":
                // iterasjon 2
        }

        return "redirect:/";
    }

    public String validerBruker(String brukernavn) {
        if (!Pattern.compile("([a-z]{2})(\\d{6})").matcher(brukernavn).find()) return "Ikke et gyldig brukernavn.";
        if (ansattService.getAnsattByBrukernavn(brukernavn) == null) return "Finner ingen ansatt på dette brukernavnet";

        return null;
    }
}