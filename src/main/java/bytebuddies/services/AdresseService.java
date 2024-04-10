package bytebuddies.services;


import bytebuddies.entities.Adresse;
import bytebuddies.entities.Postnummer;
import bytebuddies.repositories.AdresseRepository;
import bytebuddies.repositories.PostnummerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Tjenesteklasse som gir metoder for å samhandle med Adresse-entiteter.
 */
@Service
public class AdresseService {

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private PostnummerRepository postnummerRepository;

    /**
     * Lagrer en Adresse-entitet i databasen.
     * @param adresse Adresse-entiteten som skal lagres
     * @return Den lagrede Adresse-entiteten
     */
    public Adresse saveAdresse(Adresse adresse) {
        return adresseRepository.save(adresse);
    }

    /**
     * Henter en Adresse-entitet basert på gatenavn, gatenummer og postnummer.
     * @param gatenavn Navnet på gaten
     * @param gatenummer Nummeret til gaten
     * @param postnummertext Teksten som representerer postnummeret
     * @return Adresse-entiteten hvis den finnes, ellers null
     */
    public Adresse getAdresse(String gatenavn, String gatenummer, String postnummertext) {
        Postnummer postnummer = postnummerRepository.findById(postnummertext).orElse(null);
        if (postnummer == null) return null;
        return adresseRepository.findAdresseByGatenavnAndGatenummerAndPostnummer(gatenavn,gatenummer,postnummer).orElse(null);
    }
}
