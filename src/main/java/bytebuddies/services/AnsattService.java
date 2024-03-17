package bytebuddies.services;

import bytebuddies.entities.Ansatt;
import bytebuddies.repositories.AnsattRepository;
import bytebuddies.repositories.BedriftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnsattService {

    @Autowired
    private AnsattRepository ansattRepository;

    @Autowired
    private BedriftRepository bedriftRepository;

    public Optional<Ansatt> getAnsattByBrukernavn(String brukernavn) {
        return ansattRepository.findAnsattByBrukernavn(brukernavn);
    }

    public List<Ansatt> getAllAnsatte() {
        return ansattRepository.findAll();
    }

    public Ansatt saveAnsatt(Ansatt ansatt, String forkortelse) {
        Ansatt a = ansattRepository.save(ansatt);
        a.setBrukernavn(forkortelse + String.format("%06d",a.getAnsattId()));
        ansattRepository.save(a);
        return a;
    }
}
