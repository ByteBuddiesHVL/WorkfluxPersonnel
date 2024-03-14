package bytebuddies.services;

import bytebuddies.embeddable.AnsattId;
import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Bedrift;
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

    public Optional<Ansatt> getAnsattById(Integer bedriftId, String ansattId) {
        Bedrift b = bedriftRepository.findById(bedriftId).orElseGet(null);
        if (b != null) return ansattRepository.findAnsattByAnsattId_BedriftIdAndAnsattId_AnsattId(b,ansattId);
        return null;
    }

    public Optional<Ansatt> getAnsattById(AnsattId ansattId) {
        return ansattRepository.findById(ansattId);
    }

    public List<Ansatt> getAllAnsatte() {
        return ansattRepository.findAll();
    }

    public Ansatt saveAnsatt(Ansatt ansatt) {
        return ansattRepository.save(ansatt);
    }
}
