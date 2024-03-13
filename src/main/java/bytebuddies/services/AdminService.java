package bytebuddies.services;


import bytebuddies.entities.Admin;
import bytebuddies.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllByBedrift(String bedriftId) {
        return adminRepository.getAdminsByBedriftId(bedriftId);
    }

    public Optional<Admin> getAdminById(Integer adminId) {
        return adminRepository.findById(adminId);
    }

    public Optional<Admin> getAdminByBrukernavn(String brukernavn) {
        return adminRepository.getAdminByBrukernavn(brukernavn);
    }

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public void deleteAdmin(Admin admin) {
        adminRepository.delete(admin);
    }
}
