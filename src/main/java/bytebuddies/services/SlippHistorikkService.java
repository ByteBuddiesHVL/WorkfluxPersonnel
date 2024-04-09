package bytebuddies.services;

import bytebuddies.entities.Ansatt;
import bytebuddies.entities.SlippHistorikk;
import bytebuddies.repositories.SkattRepository;
import bytebuddies.repositories.SlippHistorikkRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;

@Service
public class SlippHistorikkService {

    @Autowired
    private SlippHistorikkRepository slippHistorikkRepository;

    public SlippHistorikk lagreSlipp(Ansatt ansatt, LocalDate dato, byte[] fileData) {
        return slippHistorikkRepository.save(new SlippHistorikk(ansatt,dato,null,null,null,fileData));
    }

    public SlippHistorikk lagreSlipp(Ansatt ansatt, LocalDate dato, Float brutto, Float skatt, Float netto, byte[] fileData) {
        return slippHistorikkRepository.save(new SlippHistorikk(ansatt,dato,brutto,skatt,netto,fileData));
    }

    public SlippHistorikk hentSlipp(Ansatt ansatt, LocalDate date) {
        return slippHistorikkRepository.findSlippHistorikkByAnsattIdAndAndDato(ansatt, date).orElse(null);
    }

    public List<SlippHistorikk> hentAlleSlipper() {
        return slippHistorikkRepository.findAll();
    }

    public void convertToPDF(byte[] bytes, HttpServletResponse response, String fileName) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                out.write(buffer,0,bytesRead);
            }
        }
    }
}
