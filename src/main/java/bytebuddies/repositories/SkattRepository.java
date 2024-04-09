package bytebuddies.repositories;

import bytebuddies.entities.Postnummer;
import bytebuddies.entities.Skatt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for håndtering av {@link Skatt}-entiteter i databasen.
 */
@Repository
public interface SkattRepository extends JpaRepository<Skatt, Integer> {}