package bytebuddies.repositories;

import bytebuddies.entities.Fravaer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for håndtering av {@link Fravaer}-entiteter i databasen.
 */
@Repository
public interface FravaerRepository extends JpaRepository<Fravaer, Integer> {}