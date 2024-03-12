package bytebuddies.repositories;

import bytebuddies.entities.Ansatt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnsattRepository extends JpaRepository<Ansatt, String> {
}