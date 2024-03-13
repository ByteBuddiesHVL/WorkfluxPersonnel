package bytebuddies.services;

import bytebuddies.repositories.PostnummerRepository;
import bytebuddies.repositories.SkattRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkattService {

    @Autowired
    private SkattRepository skattRepository;

}
