package bytebuddies.repositories;

import bytebuddies.entities.Adresse;
import bytebuddies.entities.Postnummer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Integer> {
    Optional<Adresse> findAdresseByGatenavnAndGatenummerAndPostnummer(String gatenavn, String gatenummer, Postnummer postnummer);
}