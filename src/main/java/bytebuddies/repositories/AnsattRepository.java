package bytebuddies.repositories;

import bytebuddies.embeddable.AnsattId;
import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Bedrift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnsattRepository extends JpaRepository<Ansatt, AnsattId> {
    List<Ansatt> findAllByAnsattId_BedriftId(Bedrift bedrift);
    Optional<Ansatt> findAnsattByAnsattId_BedriftIdAndAnsattId_AnsattId(Bedrift bedrift, String ansattId);
}