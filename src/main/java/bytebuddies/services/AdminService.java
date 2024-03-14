package bytebuddies.services;


import bytebuddies.entities.Admin;
import bytebuddies.entities.Bedrift;
import bytebuddies.repositories.AdminRepository;
import bytebuddies.repositories.BedriftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BedriftRepository bedriftRepository;

    public List<Admin> getAllByBedriftId(Integer bedriftId) {
        Bedrift b = bedriftRepository.findById(bedriftId).orElseGet(null);
        if (b != null) return adminRepository.getAdminsByBedriftId(b);
        return null;
    }

    public List<Admin> getAllByBedrift(Bedrift bedrift) {
        return adminRepository.getAdminsByBedriftId(bedrift);
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
