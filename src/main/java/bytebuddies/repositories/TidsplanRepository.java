package bytebuddies.repositories;

import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Tidsplan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TidsplanRepository extends JpaRepository<Tidsplan, Integer> {
    Optional<Tidsplan> getFirstByAnsattIdAndSluttidIsNullAndStarttidIsNotNullOrderByStarttidDesc(Ansatt ansatt);

    List<Tidsplan> getTidsplansByAnsattIdAndStarttidBetweenAndIsCalcedIsFalse(Ansatt ansatt, LocalDateTime start, LocalDateTime end);
}
