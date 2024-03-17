package bytebuddies.services;

import bytebuddies.entities.Dev;
import bytebuddies.repositories.DevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevService {

    @Autowired
    private DevRepository devRepository;

    public Dev saveDev(Dev dev) {
        return devRepository.save(dev);
    }

}
