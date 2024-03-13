package bytebuddies.services;

import bytebuddies.repositories.LonnRepository;
import bytebuddies.repositories.PostnummerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostnummerService {

    @Autowired
    private PostnummerRepository postnummerRepository;

}
