package bytebuddies.services;

import bytebuddies.repositories.SkattRepository;
import bytebuddies.repositories.SlippHistorikkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlippHistorikkService {

    @Autowired
    private SlippHistorikkRepository slippHistorikkRepository;

}
