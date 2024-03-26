package bytebuddies.controllers;

import bytebuddies.embeddable.Passord;
import bytebuddies.entities.*;
import bytebuddies.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.util.List;
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
            if (delside.equals("personal")) model.addAttribute("ansatte",getAnsattString());
            else if (delside.equals("ansatt")) {
                model.addAttribute("ansatte", getAnsattString());
                model.addAttribute("ansattListe", ansattService.getAllAnsatte());
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
            @RequestParam("stillingstype") String stillingstype,
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
            @RequestParam("stillingstype") String stillingstype,
            @RequestParam(required = false, defaultValue = "false") boolean slettAnsatt,
            HttpSession session,
            RedirectAttributes attributes
    ) {

        if (slettAnsatt) {
            ansattService.deleteAnsattByBrukernavn(brukernavn);
            return "redirect:/suite/ansatt";
        }
        String errorMessage = valServ.validerAnsatt(fornavn,etternavn,telefonnummer,epost,gatenavn,gatenummer,postnummer,stillingsprosent,stillingstype);
        if (errorMessage != null) attributes.addFlashAttribute("error", errorMessage);
        else {
            Admin admin = getLoggedInAttr(session);
            if (admin == null) {
                attributes.addFlashAttribute("error", "Du er ikke logget inn!");
                return "redirect:/suite";
            }
            Bedrift bedrift = admin.getBedriftId();
            Ansatt ansatt = ansattService.getAnsattByBrukernavn(brukernavn);

            ansatt.setFornavn(fornavn);
            ansatt.setEtternavn(etternavn);
            ansatt.setTelefonnummer(telefonnummer);
            ansatt.setEpost(epost);
            ansatt.getAdresseId().setGatenavn(gatenavn);
            ansatt.getAdresseId().setGatenummer(gatenummer);
            ansatt.getAdresseId().getPostnummer().setPostnummer(postnummer);
            ansatt.setStillingsprosent(stillingsprosent);
            ansatt.setStillingstype(stillingstype);
            ansattService.saveAnsatt(ansatt,bedrift.getForkortelse());
        }
        return "redirect:/suite/ansatt";
    }

    public Admin getLoggedInAttr(HttpSession session) {
        return (Admin) session.getAttribute("loggedin");
    }

    public String getAnsattString() {
        return ansattService.getAllAnsatte().stream()
                .map(Ansatt::toString)
                .collect(Collectors.joining(",", "[", "]"));
    }
}
