package bytebuddies.services;

import bytebuddies.entities.Bedrift;
import bytebuddies.entities.Tidsplantype;
import bytebuddies.repositories.TidsplantypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Tjenesteklasse til å utføre operasjoner relatert til tidsplantyper.
 */
@Service
public class TidsplantypeService {

    @Autowired
    private TidsplantypeRepository tidsplantypeRepository;

    /**
     * Henter tidsplantype med angitt type.
     *
     * @param type Typen som skal hentes.
     * @return Tidsplantype med angitt type, eller null hvis den ikke finnes.
     */
    public Tidsplantype getTidsplantypeByType(String type) {
        return tidsplantypeRepository.getTidsplantypeByType(type).orElse(null);
    }

    /**
     * Henter tidsplantype med angitt ID.
     *
     * @param id ID-en til tidsplantypen som skal hentes.
     * @return Tidsplantype med angitt ID, eller null hvis den ikke finnes.
     */
    public Tidsplantype getTidsplantypeById(Integer id) {
        return tidsplantypeRepository.findById(id).orElse(null);
    }

    /**
     * Henter alle tidsplantyper tilknyttet en bedrift.
     *
     * @param b Bedriften som tidsplantypene tilhører.
     * @return En liste over alle tidsplantyper tilknyttet den angitte bedriften.
     */
    public List<Tidsplantype> getTidsplantyperByBedrift(Bedrift b) {
        return tidsplantypeRepository.getTidsplantypesByBedriftId(b);
    }

}
