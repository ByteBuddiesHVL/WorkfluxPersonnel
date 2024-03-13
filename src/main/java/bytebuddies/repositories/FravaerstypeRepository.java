package bytebuddies.repositories;

import bytebuddies.entities.Fravaer;
import bytebuddies.entities.Fravaerstype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FravaerstypeRepository extends JpaRepository<Fravaerstype, Integer> {}