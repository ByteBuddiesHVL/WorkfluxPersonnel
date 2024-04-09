package bytebuddies.services;

import bytebuddies.repositories.DevRepository;
import bytebuddies.repositories.FravaerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Tjenesteklasse for å utføre operasjoner knyttet til fravær.
 */
@Service
public class FravaerService {

    @Autowired
    private FravaerRepository fravaerRepository;

}
