package bytebuddies.services;

import bytebuddies.TidsplanResult;
import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Tidsplan;
import bytebuddies.repositories.TidsplanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
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

    public Tidsplan endTidsplan(Tidsplan tidsplan, LocalDateTime ldt) {
        tidsplan.setSluttid(ldt);
        return tidsplanRepository.save(tidsplan);
    }

    public Tidsplan getCurrentTidsplan(Ansatt ansatt) {
        return tidsplanRepository.getFirstByAnsattIdAndSluttidIsNullAndStarttidIsNotNullOrderByStarttidDesc(ansatt).orElseGet(null);
    }

    /**
     * Sletter en tidsplan basert på ID.
     *
     * @param id ID for tidsplanen som skal slettes.
     */
    public void deleteTidsplan(int id) {
        tidsplanRepository.deleteById(id);
    }

    public List<Tidsplan> getTidsplaner(Ansatt ansatt, LocalDate start, LocalDate end) {
        return tidsplanRepository.getTidsplansByAnsattIdAndStarttidBetweenAndIsCalcedIsFalse(ansatt,start.atStartOfDay(),end.plusDays(1).atStartOfDay().minusMinutes(1));
    }

    /**
     *
     * @param ansatt
     * @param startDate - startdato på søk
     * @param endDate - sluttdato på søk mellom start og slutt av starttid på tidsplan
     * @return timer for en ansatt mellom to gitte datoer
     */
    public TidsplanResult getTimerForAnsatt(Ansatt ansatt, LocalDate startDate, LocalDate endDate) {
        List<Tidsplan> tidsplanList = getTidsplaner(ansatt,startDate,endDate);
        float duration = 0.00F;

        for (Tidsplan tidsplan : tidsplanList) {
            //check if type == lunsj etc...
            LocalDateTime shiftStart = tidsplan.getStarttid().withSecond(0);
            LocalDateTime shiftEnd = tidsplan.getSluttid().withSecond(0);

            duration += ChronoUnit.HOURS.between(shiftStart,shiftEnd) + ChronoUnit.MINUTES.between(shiftStart,shiftEnd)/60F;
            //check if everything goes well ...
        }

        //check if list is ok
        return new TidsplanResult(tidsplanList,duration);
    }
    public float getTimerForAnsattHittilIAr(Ansatt ansatt, LocalDate endDate) {
        return getTimerForAnsatt(ansatt, LocalDate.of(endDate.getYear(), 1, 1), endDate).getTimer();
    }

    public List<Tidsplan> getTidsplanByDate(LocalDate date) {
        return tidsplanRepository.getTidsplansByStarttidBetween(date.atStartOfDay(),date.plusDays(1).atStartOfDay().minusNanos(1));
    }
}