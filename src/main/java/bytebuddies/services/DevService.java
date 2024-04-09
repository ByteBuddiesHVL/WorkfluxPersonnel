package bytebuddies.services;

import bytebuddies.entities.Dev;
import bytebuddies.repositories.DevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Tjenesteklasse som gir metoder for å samhandle med Dev-entiteter.
 */
@Service
public class DevService {

    @Autowired
    private DevRepository devRepository;

    /**
     * Lagrer en Dev-entitet i databasen.
     * @param dev Dev-entiteten som skal lagres
     * @return Den lagrede Dev-entiteten
     */
    public Dev saveDev(Dev dev) {
        return devRepository.save(dev);
    }

}
