package bytebuddies.services;

import bytebuddies.repositories.FravaerstypeRepository;
import bytebuddies.repositories.LonnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LonnService {

    @Autowired
    private LonnRepository lonnRepository;

}
