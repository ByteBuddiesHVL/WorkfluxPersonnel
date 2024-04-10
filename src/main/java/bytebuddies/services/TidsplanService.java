package bytebuddies.services;

import bytebuddies.TidsplanResult;
import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Tidsplan;
import bytebuddies.repositories.TidsplanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    public Tidsplan getTidsplan(int id) {
        return tidsplanRepository.findById(id).orElse(null);
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

    /**
     * Avslutter den nåværende tidsplanen for en ansatt ved å angi en sluttid.
     *
     * @param ansatt Ansatt som den nåværende tidsplanen tilhører.
     * @param ldt    Tidspunktet når tidsplanen skal avsluttes.
     * @return Den endrede tidsplanen hvis den nåværende tidsplanen eksisterer, ellers null.
     */
    public Tidsplan endTidsplan(Ansatt ansatt, LocalDateTime ldt) {
        Tidsplan t = tidsplanRepository.getFirstByAnsattIdAndSluttidIsNullAndStarttidIsNotNullOrderByStarttidDesc(ansatt).orElse(null);
        if (t == null) return null;
        t.setSluttid(ldt);
        return tidsplanRepository.save(t);
    }

    /**
     * Avslutter en spesifisert tidsplan ved å angi en sluttid.
     *
     * @param tidsplan Tidsplanen som skal avsluttes.
     * @param ldt      Tidspunktet når tidsplanen skal avsluttes.
     * @return Den endrede tidsplanen.
     */
    public Tidsplan endTidsplan(Tidsplan tidsplan, LocalDateTime ldt) {
        tidsplan.setSluttid(ldt);
        return tidsplanRepository.save(tidsplan);
    }

    /**
     * Henter den nåværende tidsplanen for en ansatt.
     *
     * @param ansatt Ansatt som den nåværende tidsplanen tilhører.
     * @return Den nåværende tidsplanen hvis den finnes, ellers null.
     */
    public Tidsplan getCurrentTidsplan(Ansatt ansatt) {
        return tidsplanRepository.getFirstByAnsattIdAndSluttidIsNullAndStarttidIsNotNullOrderByStarttidDesc(ansatt).orElse(null);
    }

    /**
     * Sletter en tidsplan basert på ID.
     *
     * @param id ID for tidsplanen som skal slettes.
     */
    public void deleteTidsplan(int id) {
        tidsplanRepository.deleteById(id);
    }

    /**
     * Henter tidsplaner for en spesifisert ansatt innenfor en gitt tidsperiode.
     *
     * @param ansatt Ansatten som tidsplanene tilhører.
     * @param start  Startdatoen for tidsperioden.
     * @param end    Sluttdatoen for tidsperioden.
     * @return En liste over tidsplaner for den angitte ansatte innenfor den angitte tidsperioden.
     */
    public List<Tidsplan> getTidsplaner(Ansatt ansatt, LocalDate start, LocalDate end) {
        return tidsplanRepository.getTidsplansByAnsattIdAndStarttidBetweenAndIsCalcedIsFalse(ansatt,start.atStartOfDay(),end.plusDays(1).atStartOfDay().minusMinutes(1));
    }

    /**
     * @param ansatt - ansatt å få timer for
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

    /**
     * Henter antall timer arbeidet av en ansatt så langt i inneværende år frem til en angitt sluttdato.
     *
     * @param ansatt   Ansatten som antall timer skal hentes for.
     * @param endDate  Sluttdatoen for perioden som skal beregnes.
     * @return Antall timer arbeidet av den angitte ansatte så langt i inneværende år.
     */
    public float getTimerForAnsattHittilIAr(Ansatt ansatt, LocalDate endDate) {
        return getTimerForAnsatt(ansatt, LocalDate.of(endDate.getYear(), 1, 1), endDate).getTimer();
    }

    /**
     * Henter tidsplaner som starter på en spesifisert dato.
     *
     * @param date Datoen som tidsplanene skal hentes for.
     * @return En liste over tidsplaner som starter på den angitte datoen.
     */
    public List<Tidsplan> getTidsplanByDate(LocalDate date) {
        return tidsplanRepository.getTidsplansByStarttidBetween(date.atStartOfDay(),date.plusDays(1).atStartOfDay().minusNanos(1));
    }

    /**
     * Henter tidsplan innen to tidspunkter
     *
     * @param ansatt        ansatt for søk
     * @param start         starttidspunkt for søk
     * @param end           sluttidspunkt for søk
     * @param tidsplanId    tidsplanId hvis man redigerer på tidsplan
     * @return Tidsplan
     */
    public List<Tidsplan> sjekkOmTidsplanEksisterer(Ansatt ansatt, LocalDateTime start, LocalDateTime end, int tidsplanId) {
        return tidsplanRepository.findConflictingShifts(ansatt,tidsplanId,start,end);
    }
}