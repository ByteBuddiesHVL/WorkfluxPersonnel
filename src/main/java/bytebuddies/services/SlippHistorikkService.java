package bytebuddies.services;

import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Bedrift;
import bytebuddies.entities.SlippHistorikk;
import bytebuddies.repositories.SlippHistorikkRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;

/**
 * Tjenesteklasse for å utføre operasjoner knyttet til lønnsslipphistorikk.
 */
@Service
public class SlippHistorikkService {

    @Autowired
    private SlippHistorikkRepository slippHistorikkRepository;

    /**
     * Lagrer en lønnsslipp i databasen eller oppdaterer den hvis den allerede eksisterer.
     *
     * @param ansatt   Ansatt som lønnsslippen tilhører.
     * @param dato     Dato for lønnsslippen.
     * @param brutto   Bruttolønn for lønnsslippen.
     * @param skatt    Skattetrekk for lønnsslippen.
     * @param netto    Netto utbetalt for lønnsslippen.
     * @param timer    Antall arbeidstimer for lønnsslippen.
     * @param timelonn Timelønn for lønnsslippen.
     * @param fileData Data for den genererte PDF-filen av lønnsslippen.
     * @return Den lagrede eller oppdaterte lønnsslippen.
     */
    public SlippHistorikk lagreSlipp(Ansatt ansatt, LocalDate dato, Float brutto, Float skatt, Float netto, Float timer, Float timelonn, byte[] fileData) {
        SlippHistorikk slipp = slippHistorikkRepository.findSlippHistorikkByAnsattIdAndDato(ansatt,dato).orElse(null);
        if (slipp == null) return slippHistorikkRepository.save(new SlippHistorikk(ansatt,dato,brutto,skatt,netto,timer,timelonn,fileData));
        slipp.setBrutto(brutto);
        slipp.setSkatt(skatt);
        slipp.setNetto(netto);
        slipp.setFileData(fileData);
        slipp.setTimer(timer);
        slipp.setTimelonn(timelonn);
        return slippHistorikkRepository.save(slipp);
    }

    /**
     * Henter lønnsslippen for en spesifisert ansatt og dato.
     *
     * @param ansatt Ansatt som lønnsslippen tilhører.
     * @param date   Dato for lønnsslippen.
     * @return Lønnsslippen hvis den finnes, ellers null.
     */
    public SlippHistorikk hentSlipp(Ansatt ansatt, LocalDate date) {
        return slippHistorikkRepository.findSlippHistorikkByAnsattIdAndDato(ansatt, date).orElse(null);
    }

    /**
     * Henter alle lønnsslippene som er lagret i databasen.
     *
     * @param bedriftId     bedriften for søket
     * @return              Liste over alle lønnsslippene.
     */
    public List<SlippHistorikk> hentAlleSlipper(Bedrift bedriftId) {
        return slippHistorikkRepository.getSlippHistorikksByAnsattId_BedriftId(bedriftId);
    }

    /**
     * Konverterer en byte-array til en PDF-fil og sender den som respons til en HTTP-servlet.
     *
     * @param bytes     Byte-array som inneholder PDF-dataene.
     * @param response  HTTP-servletrespons.
     * @param fileName  Filnavn for den genererte PDF-filen.
     * @throws IOException hvis det oppstår en feil under konverteringen eller responsbehandlingen.
     */
    public void convertToPDF(byte[] bytes, HttpServletResponse response, String fileName) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                out.write(buffer,0,bytesRead);
            }
            out.flush();
        }
    }

    /**
     * Henter alle lønnsslippene for en spesifisert ansatt i inneværende år fram til en spesifisert dato.
     *
     * @param ansatt Ansatt som lønnsslippene tilhører.
     * @param date   Datoen som grensen for søket.
     * @return Liste over alle lønnsslippene for den angitte ansatte i inneværende år fram til datoen.
     */
    public List<SlippHistorikk> hentAlleSlipperThisYear(Ansatt ansatt, LocalDate date) {
        if (date.getMonthValue() == 1) return List.of(null);
        return slippHistorikkRepository.findSlippHistorikksByAnsattIdAndDatoBetween(ansatt,date.withDayOfYear(1),date.withMonth(date.getMonthValue() - 1).withDayOfMonth(1).minusDays(1));
    }
}
