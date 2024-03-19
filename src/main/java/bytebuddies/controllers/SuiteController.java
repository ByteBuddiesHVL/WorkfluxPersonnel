package bytebuddies.controllers;

import bytebuddies.embeddable.Passord;
import bytebuddies.entities.Admin;
import bytebuddies.services.AdminService;
import bytebuddies.services.PassordService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.regex.Pattern;

@Controller
public class SuiteController {

    @Autowired
    AdminService adminService;

    @Autowired
    PassordService passordService;

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

    public String validerAdmin(String brukernavn, String passord, HttpSession session) {
        Admin admin = adminService.getAdminByBrukernavn(brukernavn).orElseGet(null);
        if (admin == null) return "Feil brukernavn/passord";
        Passord p = admin.getPassord();
        if (!passordService.erKorrektPassord(passord,p.getSalt(),p.getHash())) return "Feil brukernavn/passord";

        session.setAttribute("loggedin",admin);
        return null;
    }

}
