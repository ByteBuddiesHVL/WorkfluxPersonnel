package bytebuddies.services;

import bytebuddies.entities.Postnummer;
import bytebuddies.repositories.LonnRepository;
import bytebuddies.repositories.PostnummerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostnummerService {

    @Autowired
    private PostnummerRepository postnummerRepository;

    public Postnummer findPostnummer(String postnummer) {
        return postnummerRepository.findById(postnummer).orElse(null);
    }

}
