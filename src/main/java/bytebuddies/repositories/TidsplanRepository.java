package bytebuddies.repositories;

import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Tidsplan;
import org.springframework.data.jpa.repository.JpaRepository;
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
     * Finner tidsplan mellom flere LocalDateTime objekter
     *
     * @param ansatt    ansatt for tidsplan
     * @param startS    start søk for starttid
     * @param endS      slutt søk for starttid
     * @param startE    start søk for sluttid
     * @param endE      slutt søk for sluttid
     * @return optional<Tidsplan>
     */
    Optional<Tidsplan> getTidsplanByAnsattIdAndStarttidBetweenOrStarttidBetween(Ansatt ansatt, LocalDateTime startS, LocalDateTime endS, LocalDateTime startE, LocalDateTime endE);

}
