package bytebuddies.controllers;

import bytebuddies.embeddable.Passord;
import bytebuddies.entities.Admin;
import bytebuddies.entities.Bedrift;
import bytebuddies.entities.Dev;
import bytebuddies.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Kontrollerklasse som håndterer utviklerverktøyene og utviklerfunksjonaliteten.
 */
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

    @Autowired
    StillingstypeService stillingstypeService;

    /**
     * Håndterer GET-forespørsler for /dev-tools-endepunktet og returnerer siden for utviklerverktøy.
     *
     * @return Navnet på HTML-siden som viser utviklerverktøy.
     */
    @GetMapping("/dev-tools")
    public String hentDevTools() {
        return "dev-tools";
    }

    /**
     * Håndterer POST-forespørsler for å lagre en ny utviklerbruker.
     *
     * @param brukernavn Brukernavnet til den nye utvikleren.
     * @param passord    Passordet til den nye utvikleren.
     * @return Omdirigerer tilbake til utviklerverktøy-siden.
     */
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

    /**
     * Håndterer POST-forespørsler for å lagre en ny bedrift.
     *
     * @param navn        Navnet på den nye bedriften.
     * @param forkortelse Forkortelsen til den nye bedriften.
     * @return Omdirigerer tilbake til utviklerverktøy-siden.
     */
    @PostMapping("/lagreBedrift")
    public String lagreBedrift(
            @RequestParam("navn") String navn,
            @RequestParam("forkortelse") String forkortelse
    ) {
        bedriftService.saveBedrift(new Bedrift(navn,forkortelse));
        return "redirect:/dev-tools";
    }

    /**
     * Håndterer POST-forespørsler for å lagre en ny administratorbruker.
     *
     * @param brukernavn Brukernavnet til den nye administratoren.
     * @param bedriftF  Forkortelsen til bedriften administratoren tilhører.
     * @param passord    Passordet til den nye administratoren.
     * @return Omdirigerer tilbake til utviklerverktøy-siden.
     */
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
}
