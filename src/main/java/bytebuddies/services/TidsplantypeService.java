package bytebuddies.services;

import bytebuddies.entities.Tidsplantype;
import bytebuddies.repositories.SlippHistorikkRepository;
import bytebuddies.repositories.TidsplantypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TidsplantypeService {

    @Autowired
    private TidsplantypeRepository tidsplantypeRepository;

    public Tidsplantype getTidsplantypeByType(String type) {
        return tidsplantypeRepository.getTidsplantypeByType(type).orElseGet(null);
    }

    public Tidsplantype getTidsplantypeById(Integer id) {
        return tidsplantypeRepository.findById(id).orElseGet(null);
    }

}
