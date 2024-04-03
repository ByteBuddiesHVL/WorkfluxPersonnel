package bytebuddies.services;

import bytebuddies.entities.Bedrift;
import bytebuddies.repositories.BedriftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BedriftService {

    @Autowired
    private BedriftRepository bedriftRepository;

    public Bedrift saveBedrift(Bedrift bedrift) {
        return bedriftRepository.save(bedrift);
    }

    public Bedrift findBedrift(String forkortelse) {
        return bedriftRepository.findBedriftByForkortelse(forkortelse).orElse(null);
    }

}
