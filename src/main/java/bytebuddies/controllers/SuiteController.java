package bytebuddies.controllers;

import bytebuddies.embeddable.Passord;
import bytebuddies.entities.Admin;
import bytebuddies.entities.Adresse;
import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Bedrift;
import bytebuddies.services.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.regex.Pattern;

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

    @GetMapping("/suite")
    public String getSuiteSite(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("loggedin");
        if (admin == null) return "suite-logon";
        return "suite";
    }

    @GetMapping("/login")
    public String logIn(
            @RequestParam(name = "brukernavn") String brukernavn,
            @RequestParam(name = "passord") String passord,
            RedirectAttributes attributes,
            HttpSession session
    ) {
        String errorMessage = validerAdmin(brukernavn,passord,session);
        if (errorMessage != null) attributes.addFlashAttribute("error",errorMessage);
        return "redirect:/suite";
    }

    @GetMapping("/ansattliste")
    public String showAnsattListe(Model model, HttpSession session) {
        List<Ansatt> ansattliste = ansattService.getAllAnsatte();
        model.addAttribute("ansatte", ansattliste);
        return "ansatt-list";
    }

    @PostMapping("/nyAnsatt")
    public String lagreAnsatt(
            @RequestParam("forkortelse") String bedriftF,
            @RequestParam("fornavn") String fornavn,
            @RequestParam("etternavn") String etternavn,
            @RequestParam("telefonnummer") String telefonnummer,
            @RequestParam("epost") String epost,
            @RequestParam("gatenavn") String gatenavn,
            @RequestParam("gatenummer") String gatenummer,
            @RequestParam("postnummer") String postnummer,
            @RequestParam("stillingsprosent") Float stillingsprosent,
            @RequestParam("stillingstype") String stillingstype,
            @RequestParam("passord") String passord
    ) {
        Bedrift b = bedriftService.findBedrift(bedriftF);
        Adresse a = adresseService.saveAdresse(new Adresse(gatenavn, gatenummer, postnummerService.findPostnummer(postnummer), true));

        String salt = passordService.genererTilfeldigSalt();
        String hash = passordService.hashMedSalt(passord, salt);

        Ansatt ansatt = new Ansatt(b, new Passord(hash, salt), fornavn, etternavn, telefonnummer, epost, a, true, stillingsprosent, stillingstype);
        ansattService.saveAnsatt(ansatt,bedriftF);
        return "redirect:/yalla";
    }




    public String validerAdmin(String brukernavn, String passord, HttpSession session) {
        Admin admin = adminService.getAdminByBrukernavn(brukernavn).orElseGet(null);
        if (admin == null) return "Feil brukernavn/passord";
        Passord p = admin.getPassord();
        if (!passordService.erKorrektPassord(passord,p.getSalt(),p.getHash())) return "Feil brukernavn/passord";

        session.setAttribute("loggedin",admin);
        return null;
    }

    public boolean validerNyAnsatt(Bedrift bedriftId, Passord passord, String fornavn, String etternavn,
                                String telefonnummer, String epost,
                                float stillingsprosent, String stillingstype) {
        return fornavn != null && fornavn.matches("^[A-ZÆØÅ][A-ZÆØÅa-zæøå -]{1,19}$") &&
                etternavn != null && etternavn.matches("^[A-ZÆØÅ][A-ZÆØÅa-zæøå-]{1,19}$") &&
                telefonnummer != null && telefonnummer.matches("^\\d{8}$") && epost !=null && epost.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
                && stillingstype != null && stillingstype.matches("^[A-ZÆØÅ][A-ZÆØÅa-zæøå -]{1,20}$");


    }

}
