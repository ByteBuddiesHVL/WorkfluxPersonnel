package bytebuddies.services;

import bytebuddies.embeddable.Passord;
import bytebuddies.entities.*;
import bytebuddies.repositories.AnsattRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValideringsService {

    @Autowired
    AnsattRepository ansattRepository;

    @Autowired
    AdminService adminService;

    @Autowired
    AdresseService adresseService;

    @Autowired
    PostnummerService postnummerService;

    @Autowired
    PassordService passordService;

    @Autowired
    StillingstypeService stillingstypeService;

    public Ansatt lagAnsatt(
            Bedrift bedrift,
            String fornavn,
            String etternavn,
            String telefonnummer,
            String epost,
            String gatenavn,
            String gatenummer,
            String postnummer,
            Float stillingsprosent,
            Integer stillingstypeId,
            String passord
    ) {
        Adresse adresse = adresseService.getAdresse(gatenavn,gatenummer,postnummer);
        if (adresse == null) adresse = adresseService.saveAdresse(new Adresse(gatenavn, gatenummer, postnummerService.findPostnummer(postnummer), true));
        // hvis adresse fortsatt er null, push error

        String salt = passordService.genererTilfeldigSalt();
        String hash = passordService.hashMedSalt(passord, salt);

        Stillingstype stillingstype = stillingstypeService.getStillingstype(stillingstypeId);

        return new Ansatt(bedrift, new Passord(hash, salt), fornavn, etternavn, telefonnummer, epost, adresse, true, stillingsprosent, stillingstype);
    }

    public String validerAdmin(String brukernavn, String passord, HttpSession session) {
        Admin admin = adminService.getAdminByBrukernavn(brukernavn).orElse(null);
        if (admin == null) return "Feil brukernavn/passord";
        Passord p = admin.getPassord();
        if (!passordService.erKorrektPassord(passord,p.getSalt(),p.getHash())) return "Feil brukernavn/passord";

        session.setAttribute("loggedin",admin);
        return null;
    }

    public String validerAnsatt(
            String fornavn,
            String etternavn,
            String telefonnummer,
            String epost,
            String gatenavn,
            String gatenummer,
            String postnummer,
            Float stillingsprosent,
            Integer stillingstype
    ) {
        if (!fornavn.matches("^[A-ZÆØÅ][A-ZÆØÅa-zæøå -]{1,19}$")) return "Ikke gyldig fornavn";
        if (!etternavn.matches("^[A-ZÆØÅ][A-ZÆØÅa-zæøå-]{1,19}$")) return "Ikke gyldig etternavn";
        if (!telefonnummer.matches("^\\d{8}$")) return "Ikke gyldig telefonnummer";
        if (!epost.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])")) return "Ikke gyldig epost";
        //gatenavn
        //gatenummer
        if (!postnummer.matches("^\\d{4}$")) return "Ikke gyldig postnummer";
        if (postnummerService.findPostnummer(postnummer) == null) return "Postnummer eksisterer ikke"; // postnummerdatabasen bør oppdateres ofte
        if (stillingsprosent > 100 || stillingsprosent < 0) return "Ikke gyldig stillingsprosent";
        if (stillingstypeService.getStillingstype(stillingstype) == null) return "Ingen type på denne id-en";
        return null;
    }
}
