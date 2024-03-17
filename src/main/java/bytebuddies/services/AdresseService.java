package bytebuddies.services;


import bytebuddies.entities.Adresse;
import bytebuddies.entities.Postnummer;
import bytebuddies.repositories.AdresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdresseService {

    @Autowired
    private AdresseRepository adresseRepository;

    public Adresse saveAdresse(Adresse adresse) {
        return adresseRepository.save(adresse);
    }

}
