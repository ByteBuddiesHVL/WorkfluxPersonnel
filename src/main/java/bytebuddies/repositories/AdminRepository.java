package bytebuddies.repositories;

import bytebuddies.entities.Admin;
import bytebuddies.entities.Bedrift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository-grensesnitt for å håndtere databasoperasjoner relatert til {@link Admin}-entiteter.
 * Utvider {@link JpaRepository} for å gi grunnleggende CRUD-operasjoner samt tilleggsmetoder for å hente admin-data.
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    /**
     * Finner alle admin-brukere knyttet til en spesifikk {@link Bedrift}.
     *
     * @param bedriftId Bedriften som admin-brukerne er knyttet til.
     * @return En liste av {@link Admin}-objekter som tilhører den spesifiserte bedriften.
     */
    List<Admin> getAdminsByBedriftId(Bedrift bedriftId);

    /**
     * Finner en admin-bruker basert på brukernavnet.
     *
     * @param brukernavn Brukernavnet til admin-brukeren som skal hentes.
     * @return Et {@link Optional} som inneholder {@link Admin}-objektet hvis det finnes, ellers {@link Optional#empty()}.
     */
    Optional<Admin> getAdminByBrukernavn(String brukernavn);
}