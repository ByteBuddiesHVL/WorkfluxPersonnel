package bytebuddies.repositories;

import bytebuddies.entities.Postnummer;
import bytebuddies.entities.Skatt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkattRepository extends JpaRepository<Skatt, Integer> {}