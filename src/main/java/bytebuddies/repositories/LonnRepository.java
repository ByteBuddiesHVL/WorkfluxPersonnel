package bytebuddies.repositories;

import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Lonn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Repository for håndtering av {@link Lonn}-entiteter i databasen.
 */
@Repository
public interface LonnRepository extends JpaRepository<Lonn, Integer> {}