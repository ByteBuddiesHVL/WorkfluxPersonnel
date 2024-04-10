package bytebuddies.services;

import bytebuddies.repositories.FravaerstypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Tjenesteklasse for å utføre operasjoner knyttet til fraværstyper.
 */
@Service
public class FravaerstypeService {

    @Autowired
    private FravaerstypeRepository fravaerstypeRepository;

}
