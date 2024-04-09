package bytebuddies.services;


import bytebuddies.entities.Admin;
import bytebuddies.entities.Bedrift;
import bytebuddies.repositories.AdminRepository;
import bytebuddies.repositories.BedriftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Tjenesteklassen gir metoder for å samhandle med Admin-entiteter.
 */
@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BedriftRepository bedriftRepository;

    /**
     * Henter en liste over administratorer tilknyttet den angitte bedrifts-IDen.
     * @param bedriftId ID-en til bedriften
     * @return En liste over administratorer tilknyttet bedriften
     */
    public List<Admin> getAllByBedriftId(Integer bedriftId) {
        Bedrift b = bedriftRepository.findById(bedriftId).orElse(null);
        if (b != null) return adminRepository.getAdminsByBedriftId(b);
        return null;
    }

    /**
     * Henter en liste over administratorer tilknyttet den angitte bedriften.
     * @param bedrift Bedriftsobjektet
     * @return En liste over administratorer tilknyttet bedriften
     */
    public List<Admin> getAllByBedrift(Bedrift bedrift) {
        return adminRepository.getAdminsByBedriftId(bedrift);
    }

    /**
     * Henter en administrator etter ID.
     * @param adminId ID-en til administrator
     * @return En Optional som inneholder administratoren hvis den finnes, ellers tom
     */
    public Optional<Admin> getAdminById(Integer adminId) {
        return adminRepository.findById(adminId);
    }

    /**
     * Henter en administrator etter brukernavn.
     * @param brukernavn Brukernavnet til administrator
     * @return En Optional som inneholder administratoren hvis den finnes, ellers tom
     */
    public Optional<Admin> getAdminByBrukernavn(String brukernavn) {
        return adminRepository.getAdminByBrukernavn(brukernavn);
    }

    /**
     * Lagrer en administrator-entitet.
     * @param admin Administratoren som skal lagres
     * @return Den lagrede administratoren
     */
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    /**
     * Sletter en administrator-entitet.
     * @param admin Administratoren som skal slettes
     */
    public void deleteAdmin(Admin admin) {
        adminRepository.delete(admin);
    }
}
