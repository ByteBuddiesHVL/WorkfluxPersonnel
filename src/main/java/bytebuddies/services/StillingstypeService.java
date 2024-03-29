package bytebuddies.services;

import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Stillingstype;
import bytebuddies.repositories.AnsattRepository;
import bytebuddies.repositories.StillingstypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StillingstypeService {

    @Autowired
    private StillingstypeRepository stillingstypeRepository;

    public Stillingstype getStillingstype(Integer id) {
        return stillingstypeRepository.findById(id).orElseGet(null);
    }
}