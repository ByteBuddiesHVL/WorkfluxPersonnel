package bytebuddies.services;


import bytebuddies.entities.Adresse;
import bytebuddies.entities.Postnummer;
import bytebuddies.repositories.AdresseRepository;
import bytebuddies.repositories.PostnummerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdresseService {

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private PostnummerRepository postnummerRepository;

    public Adresse saveAdresse(Adresse adresse) {
        return adresseRepository.save(adresse);
    }

    public Adresse getAdresse(String gatenavn, String gatenummer, String postnummertext) {
        Postnummer postnummer = postnummerRepository.findById(postnummertext).orElse(null);
        if (postnummer == null) return null;
        return adresseRepository.findAdresseByGatenavnAndGatenummerAndPostnummer(gatenavn,gatenummer,postnummer).orElse(null);
    }
}
