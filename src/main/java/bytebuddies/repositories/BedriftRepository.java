package bytebuddies.repositories;

import bytebuddies.entities.Bedrift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BedriftRepository extends JpaRepository<Bedrift, Integer> {
    Optional<Bedrift> findBedriftByForkortelse(String forkortelse);
}