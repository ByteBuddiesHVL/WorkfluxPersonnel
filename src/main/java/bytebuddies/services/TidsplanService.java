package bytebuddies.services;

import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Tidsplan;
import bytebuddies.repositories.TidsplanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Tjeneste for å håndtere CRUD-operasjoner for {@link Tidsplan}-entiteten.
 */
@Service
public class TidsplanService {

    @Autowired
    private TidsplanRepository tidsplanRepository;

    /**
     * Henter alle tidsplaner fra databasen.
     *
     * @return en liste av {@link Tidsplan}.
     */
    public List<Tidsplan> getALlTidsplaner() {
        return tidsplanRepository.findAll();
    }

    /**
     * Henter en spesifikk tidsplan basert på ID.
     *
     * @param id ID for den ønskede tidsplanen.
     * @return et {@link Optional} av {@link Tidsplan}.
     */
    public Optional<Tidsplan> getTidsplan(int id) {
        return tidsplanRepository.findById(id);
    }

    /**
     * Lagrer en tidsplan i databasen. Brukes for både å opprette nye og oppdatere eksisterende tidsplaner.
     *
     * @param tidsplan {@link Tidsplan}-objektet som skal lagres.
     * @return det lagrede {@link Tidsplan}-objektet.
     */
    public Tidsplan saveTidsplan(Tidsplan tidsplan) {
        return tidsplanRepository.save(tidsplan);
    }

    public Tidsplan endTidsplan(Ansatt ansatt, LocalDateTime ldt) {
        Tidsplan t = tidsplanRepository.getFirstByAnsattIdAndSluttidIsNullAndStarttidIsNotNullOrderByStarttidDesc(ansatt).orElseGet(null);
        if (t == null) return null;
        t.setSluttid(ldt);
        return tidsplanRepository.save(t);
    }

    /**
     * Sletter en tidsplan basert på ID.
     *
     * @param id ID for tidsplanen som skal slettes.
     */
    public void deleteTidsplan(int id) {
        tidsplanRepository.deleteById(id);
    }

}
