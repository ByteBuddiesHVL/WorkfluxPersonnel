package bytebuddies.repositories;

import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Skatt;
import bytebuddies.entities.SlippHistorikk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SlippHistorikkRepository extends JpaRepository<SlippHistorikk, Integer> {
    Optional<SlippHistorikk> findSlippHistorikkByAnsattIdAndAndDato(Ansatt ansatt, LocalDate date);

    List<SlippHistorikk> findSlippHistorikksByAnsattIdAndDatoBetween(Ansatt ansatt, LocalDate startOfYear, LocalDate until);
}