package bytebuddies.repositories;

import bytebuddies.entities.Bedrift;
import bytebuddies.entities.Tidsplan;
import bytebuddies.entities.Tidsplantype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TidsplantypeRepository extends JpaRepository<Tidsplantype, Integer> {
    Optional<Tidsplantype> getTidsplantypeByType(String type);

    List<Tidsplantype> getTidsplantypesByBedriftId(Bedrift bedriftId);
}
