package bytebuddies.services;

import bytebuddies.entities.Bedrift;
import bytebuddies.repositories.BedriftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Tjenesteklasse som gir metoder for å samhandle med Bedrift-entiteter.
 */
@Service
public class BedriftService {

    @Autowired
    private BedriftRepository bedriftRepository;

    /**
     * Lagrer en bedrift i databasen.
     * @param bedrift Bedrift-entiteten som skal lagres
     * @return Den lagrede Bedrift-entiteten
     */
    public Bedrift saveBedrift(Bedrift bedrift) {
        return bedriftRepository.save(bedrift);
    }

    /**
     * Finn en bedrift basert på forkortelsen.
     * @param forkortelse Forkortelsen til bedriften
     * @return Bedrift-entiteten hvis den finnes, ellers null
     */
    public Bedrift findBedrift(String forkortelse) {
        return bedriftRepository.findBedriftByForkortelse(forkortelse).orElse(null);
    }

}
