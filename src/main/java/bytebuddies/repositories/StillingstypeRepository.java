package bytebuddies.repositories;

import bytebuddies.entities.Bedrift;
import bytebuddies.entities.Stillingstype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StillingstypeRepository extends JpaRepository<Stillingstype, Integer> {
    List<Stillingstype> findAllByBedriftId(Bedrift bedriftId);
}