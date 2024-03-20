package bytebuddies.controllers;

import bytebuddies.embeddable.AnsattId;
import bytebuddies.embeddable.Passord;
import bytebuddies.entities.*;
import bytebuddies.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DevController {

    @Autowired
    DevService devService;

    @Autowired
    PassordService passordService;

    @Autowired
    AnsattService ansattService;

    @Autowired
    AdminService adminService;

    @Autowired
    BedriftService bedriftService;

    @Autowired
    AdresseService adresseService;

    @Autowired
    PostnummerService postnummerService;

    @GetMapping("/dev-tools")
    public String hentDevTools() {
        return "dev-tools";
    }

    @PostMapping("/largeDev")
    public String lagreDeveloper(
            @RequestParam("brukernavn") String brukernavn,
            @RequestParam("passord") String passord
    ) {
        String salt = passordService.genererTilfeldigSalt();
        String hash = passordService.hashMedSalt(passord, salt);
        devService.saveDev(new Dev(brukernavn, new Passord(hash,salt)));
        return "redirect:/dev-tools";
    }

    @PostMapping("/lagreBedrift")
    public String lagreBedrift(
            @RequestParam("navn") String navn,
            @RequestParam("forkortelse") String forkortelse
    ) {
        bedriftService.saveBedrift(new Bedrift(navn,forkortelse));
        return "redirect:/dev-tools";
    }

    @PostMapping("/lagreAdmin")
    public String lagreAdmin(
            @RequestParam("brukernavn") String brukernavn,
            @RequestParam("forkortelse") String bedriftF,
            @RequestParam("passord") String passord
    ) {
        Bedrift bedrift = bedriftService.findBedrift(bedriftF);

        String salt = passordService.genererTilfeldigSalt();
        String hash = passordService.hashMedSalt(passord, salt);

        adminService.saveAdmin(new Admin(bedrift,brukernavn,new Passord(hash,salt)));
        return "redirect:/dev-tools";
    }

    @PostMapping("/lagreAnsatt")
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
        if (b == null) return ""; //something
        Adresse a = adresseService.saveAdresse(new Adresse(gatenavn, gatenummer, postnummerService.findPostnummer(postnummer), true));

        String salt = passordService.genererTilfeldigSalt();
        String hash = passordService.hashMedSalt(passord, salt);

        Ansatt ansatt = new Ansatt(b, new Passord(hash, salt), fornavn, etternavn, telefonnummer, epost, a, true, stillingsprosent, stillingstype);
        ansattService.saveAnsatt(ansatt,bedriftF);
        return "redirect:/dev-tools";
    }
}
