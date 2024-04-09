package bytebuddies.services;

import bytebuddies.entities.Postnummer;
import bytebuddies.repositories.LonnRepository;
import bytebuddies.repositories.PostnummerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Tjenesteklasse for å utføre operasjoner knyttet til postnumre.
 */
@Service
public class PostnummerService {

    @Autowired
    private PostnummerRepository postnummerRepository;

    /**
     * Finn et postnummer basert på postnummeret.
     *
     * @param postnummer Postnummeret som skal søkes etter.
     * @return Postnummeret hvis det finnes, ellers null.
     */
    public Postnummer findPostnummer(String postnummer) {
        return postnummerRepository.findById(postnummer).orElse(null);
    }

}
