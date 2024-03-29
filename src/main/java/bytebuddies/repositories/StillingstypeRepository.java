package bytebuddies.repositories;

import bytebuddies.entities.Stillingstype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StillingstypeRepository extends JpaRepository<Stillingstype, Integer> {}