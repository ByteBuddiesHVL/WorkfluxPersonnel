package bytebuddies.repositories;

import bytebuddies.entities.Bedrift;
import bytebuddies.entities.Tidsplantype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for håndtering av {@link Tidsplantype}-entiteter i databasen.
 */
@Repository
public interface TidsplantypeRepository extends JpaRepository<Tidsplantype, Integer> {
    /**
     * Hent tidsplantypen basert på typen.
     *
     * @param type Typen til tidsplantypen.
     * @return Tidsplantypen som matcher typen, hvis den finnes.
     */
    Optional<Tidsplantype> getTidsplantypeByType(String type);

    /**
     * Hent tidsplantyper tilhørende en spesifikk bedrift.
     *
     * @param bedriftId ID-en til bedriften.
     * @return En liste med tidsplantyper som tilhører den angitte bedriften.
     */
    List<Tidsplantype> getTidsplantypesByBedriftId(Bedrift bedriftId);

    Optional<Tidsplantype> getTidsplantypeByBedriftIdAndType(Bedrift bedriftId, String type);
}
