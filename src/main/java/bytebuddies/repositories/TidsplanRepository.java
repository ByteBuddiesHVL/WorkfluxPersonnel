package bytebuddies.repositories;

import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Tidsplan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository for håndtering av {@link Tidsplan}-entiteter i databasen.
 */
@Repository
public interface TidsplanRepository extends JpaRepository<Tidsplan, Integer> {

    /**
     * Hent den nyeste tidsplanen for en ansatt hvor sluttid er null og starttid ikke er null,
     * ordnet etter starttid i synkende rekkefølge.
     *
     * @param ansatt Ansatt som tidsplanen tilhører.
     * @return Den nyeste tidsplanen som matcher kriteriene.
     */
    Optional<Tidsplan> getFirstByAnsattIdAndSluttidIsNullAndStarttidIsNotNullOrderByStarttidDesc(Ansatt ansatt);

    /**
     * Hent tidsplaner for en ansatt som starter og slutter innenfor et gitt tidsintervall,
     * og som ikke er beregnet.
     *
     * @param ansatt Ansatt som tidsplanene tilhører.
     * @param start  Starttidspunkt for tidsintervallet.
     * @param end    Sluttidspunkt for tidsintervallet.
     * @return En liste med tidsplaner som matcher kriteriene.
     */
    List<Tidsplan> getTidsplansByAnsattIdAndStarttidBetweenAndIsCalcedIsFalse(Ansatt ansatt, LocalDateTime start, LocalDateTime end);

    /**
     * Hent tidsplaner som starter og slutter innenfor et gitt daglig tidsintervall.
     *
     * @param startOfDay Starttidspunkt for dagen.
     * @param endOfDay   Sluttidspunkt for dagen.
     * @return En liste med tidsplaner som matcher kriteriene.
     */
    List<Tidsplan> getTidsplansByStarttidBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

    /**
     * Finner tidsplaner mellom flere LocalDateTime objekter
     *
     * @param ansattId      ansatt ID for søk
     * @param starttid      starttid for søk
     * @param sluttid       sluttid for søk
     * @param tidsplanId    tidsplan ID ved endring av tidsplan
     * @return List<Tidsplan>
     */
    @Query("SELECT t FROM Tidsplan t WHERE t.ansattId = :ansattId AND t.tidsplanId <> :tidsplanId AND ((t.starttid <= :starttid AND t.sluttid > :starttid) OR (t.starttid < :sluttid AND t.sluttid >= :sluttid) OR (t.starttid >= :starttid AND t.sluttid <= :sluttid))")
    List<Tidsplan> findConflictingShifts(@Param("ansattId") Ansatt ansattId, @Param("tidsplanId") int tidsplanId, @Param("starttid") LocalDateTime starttid, @Param("sluttid") LocalDateTime sluttid);
}
