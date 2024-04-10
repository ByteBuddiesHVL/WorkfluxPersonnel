package bytebuddies.repositories;

import bytebuddies.entities.Lonn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository for håndtering av {@link Lonn}-entiteter i databasen.
 */
@Repository
public interface LonnRepository extends JpaRepository<Lonn, Integer> {}