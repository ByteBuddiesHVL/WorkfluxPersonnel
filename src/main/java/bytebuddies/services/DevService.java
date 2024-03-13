package bytebuddies.services;

import bytebuddies.repositories.DevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevService {

    @Autowired
    private DevRepository devRepository;

}
