package bytebuddies.repositories;

import bytebuddies.entities.Tidsplan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TidsplanRepository extends JpaRepository<Tidsplan, Integer> {
}
