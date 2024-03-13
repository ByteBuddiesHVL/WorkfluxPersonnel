package bytebuddies.services;

import bytebuddies.repositories.FravaerRepository;
import bytebuddies.repositories.FravaerstypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FravaerstypeService {

    @Autowired
    private FravaerstypeRepository fravaerstypeRepository;

}
