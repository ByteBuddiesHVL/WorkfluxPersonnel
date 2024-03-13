package bytebuddies.services;

import bytebuddies.repositories.BedriftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BedriftService {

    @Autowired
    private BedriftRepository bedriftRepository;

}
