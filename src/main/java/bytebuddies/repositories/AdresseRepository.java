package bytebuddies.repositories;

import bytebuddies.entities.Adresse;
import bytebuddies.entities.Postnummer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data repository for å håndtere operasjoner for {@link Adresse}-entiteter i Workflux-systemet.
 * Tilbyr standard CRUD-operasjoner gjennom arv fra {@link JpaRepository}.
 */
@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Integer> {
    /**
     * Finner en adresse basert på gatenavn, gatenummer og postnummer.
     * Denne metoden muliggjør søk etter spesifikke adresser i databasen, ved å matche på både gatenavn,
     * gatenummer og det tilknyttede {@link Postnummer}.
     *
     * @param gatenavn    Gatenavnet til adressen som søkes etter.
     * @param gatenummer  Gatenummeret til adressen som søkes etter.
     * @param postnummer  {@link Postnummer}-objektet som adressen er tilknyttet.
     * @return Et {@link Optional} objekt som inneholder den funnede {@link Adresse} hvis tilgjengelig,
     *         ellers {@link Optional#empty()}.
     */
    Optional<Adresse> findAdresseByGatenavnAndGatenummerAndPostnummer(String gatenavn, String gatenummer, Postnummer postnummer);
}