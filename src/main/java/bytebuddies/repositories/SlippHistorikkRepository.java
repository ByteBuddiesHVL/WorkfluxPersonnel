package bytebuddies.repositories;

import bytebuddies.entities.Ansatt;
import bytebuddies.entities.SlippHistorikk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository for håndtering av {@link SlippHistorikk}-entiteter i databasen.
 */
@Repository
public interface SlippHistorikkRepository extends JpaRepository<SlippHistorikk, Integer> {
    /**
     * Finn et lønnsslipp for en gitt ansatt på en bestemt dato.
     *
     * @param ansatt Den ansatte som lønnsslippet tilhører.
     * @param date   Datoen for lønnsslippet.
     * @return En valgfri {@link SlippHistorikk} hvis den finnes.
     */
    Optional<SlippHistorikk> findSlippHistorikkByAnsattIdAndAndDato(Ansatt ansatt, LocalDate date);

    /**
     * Finn lønnsslippene for en gitt ansatt innenfor et gitt tidsintervall.
     *
     * @param ansatt     Den ansatte som lønnsslippene tilhører.
     * @param startOfYear Startdato for tidsintervallet.
     * @param until      Sluttdato for tidsintervallet.
     * @return En liste med {@link SlippHistorikk} hvis de finnes.
     */
    List<SlippHistorikk> findSlippHistorikksByAnsattIdAndDatoBetween(Ansatt ansatt, LocalDate startOfYear, LocalDate until);
}