package bytebuddies.repositories;

import bytebuddies.entities.Bedrift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data repository for å håndtere databasoperasjoner for {@link Bedrift}-entiteter.
 * Tilbyr standard CRUD-operasjoner via arv fra {@link JpaRepository} og tilleggsmetoder for spesifikk søk.
 */
@Repository
public interface BedriftRepository extends JpaRepository<Bedrift, Integer> {
    /**
     * Finner en bedrift basert på dens forkortelse.
     *
     * @param forkortelse Forkortelsen til bedriften som søkes etter.
     * @return Et {@link Optional} objekt som inneholder den funnede {@link Bedrift} hvis tilgjengelig,
     *         ellers {@link Optional#empty()}.
     *         Dette gjør det mulig å håndtere tilfeller hvor bedriften ikke finnes, uten å kaste en exception.
     */
    Optional<Bedrift> findBedriftByForkortelse(String forkortelse);
}