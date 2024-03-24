package bytebuddies.repositories;

import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Lonn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LonnRepository extends JpaRepository<Lonn, Integer> {
    Optional<Lonn> getTimelonnByAnsattId(Ansatt ansatt);

    Optional<Lonn> getArslonnByAnsattId(Ansatt ansatt);
}