package bytebuddies.repositories;

import bytebuddies.entities.Dev;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for å håndtere databasetransaksjoner relatert til {@link Dev}-entiteter.
 */
@Repository
public interface DevRepository extends JpaRepository<Dev, Integer> {}