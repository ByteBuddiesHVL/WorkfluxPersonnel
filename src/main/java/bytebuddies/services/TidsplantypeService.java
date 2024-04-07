package bytebuddies.services;

import bytebuddies.entities.Bedrift;
import bytebuddies.entities.Tidsplantype;
import bytebuddies.repositories.SlippHistorikkRepository;
import bytebuddies.repositories.TidsplantypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TidsplantypeService {

    @Autowired
    private TidsplantypeRepository tidsplantypeRepository;

    public Tidsplantype getTidsplantypeByType(String type) {
        return tidsplantypeRepository.getTidsplantypeByType(type).orElse(null);
    }

    public Tidsplantype getTidsplantypeById(Integer id) {
        return tidsplantypeRepository.findById(id).orElse(null);
    }

    public List<Tidsplantype> getTidsplantyperByBedrift(Bedrift b) {
        return tidsplantypeRepository.getTidsplantypesByBedriftId(b);
    }

}
