package bytebuddies.repositories;

import bytebuddies.entities.Lonn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LonnRepository extends JpaRepository<Lonn, Integer> {}