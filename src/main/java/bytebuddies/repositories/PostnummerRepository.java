package bytebuddies.repositories;

import bytebuddies.entities.Postnummer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for håndtering av {@link Postnummer}-entiteter i databasen.
 */
@Repository
public interface PostnummerRepository extends JpaRepository<Postnummer, String> {}