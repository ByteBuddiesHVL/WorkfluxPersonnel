package bytebuddies.services;

import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Bedrift;
import bytebuddies.entities.Stillingstype;
import bytebuddies.repositories.AnsattRepository;
import bytebuddies.repositories.StillingstypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Tjenesteklasse for å utføre operasjoner knyttet til stillingstyper.
 */
@Service
public class StillingstypeService {

    @Autowired
    private StillingstypeRepository stillingstypeRepository;

    /**
     * Henter en stillingstype basert på ID.
     *
     * @param id ID-en til stillingstypen som skal hentes.
     * @return Stillingstypen med angitt ID hvis den finnes, ellers null.
     */
    public Stillingstype getStillingstype(Integer id) {
        return stillingstypeRepository.findById(id).orElse(null);
    }

    /**
     * Henter alle stillingstyper tilknyttet en spesifisert bedrift.
     *
     * @param bedrift Bedriften som stillingstypene tilhører.
     * @return Liste over alle stillingstyper tilknyttet den angitte bedriften.
     */
    public List<Stillingstype> getAlleTyper(Bedrift bedrift) {
        return stillingstypeRepository.findAllByBedriftId(bedrift);
    }
}