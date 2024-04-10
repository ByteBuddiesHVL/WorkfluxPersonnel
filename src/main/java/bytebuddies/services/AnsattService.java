package bytebuddies.services;

import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Bedrift;
import bytebuddies.repositories.AnsattRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Tjenesteklasse som gir metoder for å samhandle med Ansatt-entiteter.
 */
@Service
public class AnsattService {

    @Autowired
    private AnsattRepository ansattRepository;

    /**
     * Henter en ansatt basert på brukernavn.
     * @param brukernavn Brukernavnet til den ansatte
     * @return Ansatt-entiteten hvis den finnes, ellers null
     */
    public Ansatt getAnsattByBrukernavn(String brukernavn) {
        return ansattRepository.findAnsattByBrukernavn(brukernavn).orElse(null);
    }

    /**
     * Sletter en ansatt basert på brukernavn.
     * @param brukernavn Brukernavnet til den ansatte som skal slettes
     */
    public void deleteAnsattByBrukernavn(String brukernavn) {
        Ansatt ansatt = getAnsattByBrukernavn(brukernavn);
        if (ansatt != null) {
            ansattRepository.delete(ansatt);
        }
    }

    /**
     * Henter alle ansatte.
     * @return En liste over alle ansatte
     */
    public List<Ansatt> getAllAnsatte() {
        return ansattRepository.findAll();
    }

    /**
     * Lagrer en ny ansatt.
     * Setter brukernavnet basert på bedriftsforkortelse og ansattens ID.
     * @param ansatt Ansatt-entiteten som skal lagres
     * @param forkortelse Forkortelse for bedriften
     * @return Den lagrede Ansatt-entiteten
     */
    public Ansatt saveAnsatt(Ansatt ansatt, String forkortelse) {
        Ansatt a = ansattRepository.save(ansatt);
        a.setBrukernavn(forkortelse + String.format("%06d",a.getAnsattId()));
        ansattRepository.save(a);
        return a;
    }

    /**
     * Henter alle ansatte tilhørende en bestemt bedrift.
     * @param bedriftId Bedrift-entiteten som ansatte tilhører
     * @return En liste over ansatte tilhørende den angitte bedriften
     */
    public List<Ansatt> getAllAnsatteByBedrift(Bedrift bedriftId) {
        return ansattRepository.findAllByBedriftId(bedriftId);
    }
}