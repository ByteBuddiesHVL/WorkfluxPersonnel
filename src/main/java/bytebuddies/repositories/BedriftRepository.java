package bytebuddies.repositories;

import bytebuddies.entities.Bedrift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedriftRepository extends JpaRepository<Bedrift, Integer> {}