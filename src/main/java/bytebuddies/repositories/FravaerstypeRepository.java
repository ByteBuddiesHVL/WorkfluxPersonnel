package bytebuddies.repositories;

import bytebuddies.entities.Fravaerstype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for håndtering av {@link Fravaerstype}-entiteter i databasen.
 */
@Repository
public interface FravaerstypeRepository extends JpaRepository<Fravaerstype, Integer> {}