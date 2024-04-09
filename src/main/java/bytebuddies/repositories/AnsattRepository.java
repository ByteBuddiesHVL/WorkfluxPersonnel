package bytebuddies.repositories;

import bytebuddies.embeddable.AnsattId;
import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Bedrift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data repository for å håndtere databasoperasjoner for {@link Ansatt}-entiteter.
 * Gir tilgang til standard CRUD-operasjoner via arv fra {@link JpaRepository} og inkluderer ytterligere metoder
 * for å finne ansatte basert på spesifikke kriterier.
 */
@Repository
public interface AnsattRepository extends JpaRepository<Ansatt, Integer> {
    /**
     * Finner alle ansatte tilknyttet en spesifikk {@link Bedrift}.
     *
     * @param bedriftId Identifikatoren for bedriften ansatte skal finnes for.
     * @return En liste av {@link Ansatt}-objekter som er tilknyttet den oppgitte bedriften.
     */
    List<Ansatt> findAllByBedriftId(Bedrift bedriftId);

    /**
     * Finner en ansatt basert på brukernavnet.
     *
     * @param brukernavn Brukernavnet til den ansatte som skal finnes.
     * @return Et {@link Optional} objekt som inneholder den funnede {@link Ansatt} hvis tilgjengelig,
     *         ellers {@link Optional#empty()}.
     */
    Optional<Ansatt> findAnsattByBrukernavn(String brukernavn);
}