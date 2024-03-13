package bytebuddies.repositories;

import bytebuddies.embeddable.AnsattId;
import bytebuddies.entities.Ansatt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnsattRepository extends JpaRepository<Ansatt, AnsattId> {
    List<Ansatt> findAllByBedrift(String forkortelse);
    List<Ansatt> findAllByBedriftId(Integer id);
    Optional<Ansatt> findAnsattByAnsattId_BedriftIdAndAnsattId_AnsattId(String bedriftId, String ansattId);
}