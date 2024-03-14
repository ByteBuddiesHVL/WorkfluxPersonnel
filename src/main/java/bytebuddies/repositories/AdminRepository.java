package bytebuddies.repositories;

import bytebuddies.entities.Admin;
import bytebuddies.entities.Bedrift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    List<Admin> getAdminsByBedriftId(Bedrift bedriftId);
    Optional<Admin> getAdminByBrukernavn(String brukernavn);
}