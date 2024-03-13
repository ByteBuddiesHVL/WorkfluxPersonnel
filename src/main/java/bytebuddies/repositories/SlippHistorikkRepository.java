package bytebuddies.repositories;

import bytebuddies.entities.Skatt;
import bytebuddies.entities.SlippHistorikk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlippHistorikkRepository extends JpaRepository<SlippHistorikk, Integer> {}