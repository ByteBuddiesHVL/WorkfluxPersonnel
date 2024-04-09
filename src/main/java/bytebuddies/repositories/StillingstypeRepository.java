package bytebuddies.repositories;

import bytebuddies.entities.Bedrift;
import bytebuddies.entities.Stillingstype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for håndtering av {@link Stillingstype}-entiteter i databasen.
 */
@Repository
public interface StillingstypeRepository extends JpaRepository<Stillingstype, Integer> {
    /**
     * Finn alle stillingstyper tilhørende en gitt bedrift.
     *
     * @param bedriftId Bedriften som stillingstypene tilhører.
     * @return En liste med {@link Stillingstype} tilhørende bedriften.
     */
    List<Stillingstype> findAllByBedriftId(Bedrift bedriftId);
}