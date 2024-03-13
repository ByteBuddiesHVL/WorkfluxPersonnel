package bytebuddies.services;

import bytebuddies.embeddable.AnsattId;
import bytebuddies.entities.Ansatt;
import bytebuddies.repositories.AnsattRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnsattService {

    @Autowired
    private AnsattRepository ansattRepository;

    public Optional<Ansatt> getAnsattById(String bedriftId, String ansattId) {
        return ansattRepository.findAnsattByAnsattId_BedriftIdAndAnsattId_AnsattId(bedriftId,ansattId);
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
