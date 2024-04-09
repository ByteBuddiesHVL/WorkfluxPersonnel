package bytebuddies.services;

import bytebuddies.TidsplanResult;
import bytebuddies.entities.*;
import bytebuddies.repositories.AnsattRepository;
import bytebuddies.repositories.LonnRepository;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class LonnService {

    @Autowired
    private LonnRepository lonnRepository;

    @Autowired
    private AnsattRepository ansattRepository;

    @Autowired
    private TidsplanService tidsplanService;

    @Autowired
    private SlippHistorikkService slippHistorikkService;

    public Lonn lagreLonn(Float timelonn, Float arslonn) {
        return lonnRepository.save(new Lonn(timelonn,arslonn,LocalDate.now()));
    }

    private TidsplanResult tidsplanResult;
    private Ansatt ansatt;

    private double skatt = 0.0;
    private float timelonn = 0.0F;
    private float brutto = 0.0F;
    private float netto = 0.0F;
    private float timer = 0.0F;



    public List<SlippHistorikk> genererLonnsslippForAlleAnsatte(Bedrift bedriftId, LocalDate startDate, LocalDate endDate, LocalDate utbetalingsDato) {
        List<Ansatt> ansatte = ansattRepository.findAllByBedriftId(bedriftId);
        return ansatte.stream().map(ansatt -> {
            try {
                return genererLonnsslippForAnsatt(ansatt, startDate, endDate,utbetalingsDato);
            } catch (IOException e) {
                //todo return error
            }return null;
        }).collect(Collectors.toList());
    }

    public SlippHistorikk genererLonnsslippForAnsatt(Ansatt ansatt, LocalDate startDate, LocalDate endDate, LocalDate utbetalingsDato) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        this.ansatt = ansatt;
        tidsplanResult = tidsplanService.getTimerForAnsatt(ansatt, startDate, endDate);
        timer = tidsplanResult.getTimer();

        float[] columnWidths1 = {170, 170};
        Table slippInformasjonTabell = new Table(UnitValue.createPointArray(columnWidths1));
        //Rad1
        slippInformasjonTabell.addCell(new Cell(1, 2).add(new Paragraph("Lønnsslipp Informasjon").setFont(font)).setTextAlignment(TextAlignment.CENTER));
        //Rad2
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph("Lønnsmottaker").setFont(font)).setBorder(Border.NO_BORDER).setBorderLeft(new SolidBorder(1)));
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph(ansatt.getFornavn() + " " + ansatt.getEtternavn())).setBorder(Border.NO_BORDER).setBorderRight(new SolidBorder(1)));
        //Rad3
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph("Adresse").setFont(font)).setBorder(Border.NO_BORDER).setBorderLeft(new SolidBorder(1)));
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph(String.valueOf(ansatt.getAdresseId().toString()))).setBorder(Border.NO_BORDER).setBorderRight(new SolidBorder(1)));
        //Rad4
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph("Ansatt ID").setFont(font)).setBorder(Border.NO_BORDER).setBorderLeft(new SolidBorder(1)));
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph(ansatt.getBrukernavn())).setBorder(Border.NO_BORDER).setBorderRight(new SolidBorder(1)));
        //Rad6
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph("Firmanavn / Avsender").setFont(font)).setBorder(Border.NO_BORDER).setBorderLeft(new SolidBorder(1)));
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph(String.valueOf(ansatt.getBedriftId().getNavn()))).setBorder(Border.NO_BORDER).setBorderRight(new SolidBorder(1)));
        //Rad7
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph("LønnsslippId").setFont(font)).setBorder(Border.NO_BORDER).setBorderLeft(new SolidBorder(1)));
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER).setBorderRight(new SolidBorder(1)));
        //Rad8
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph("Dato utbetalt").setFont(font)).setBorder(Border.NO_BORDER).setBorderLeft(new SolidBorder(1)).setBorderBottom(new SolidBorder(1)));
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph(String.valueOf(utbetalingsDato))).setBorder(Border.NO_BORDER).setBorderRight(new SolidBorder(1)).setBorderBottom(new SolidBorder(1)));


        float[] columnWidths2 = {150, 90, 90, 90, 90, 90, 90};
        Table lonnslippTabell = new Table(UnitValue.createPointArray(columnWidths2));
        //Rad1
        lonnslippTabell.addCell(new Cell(2, 1).add(new Paragraph("Lønn/Trekk").setFont(font)));
        lonnslippTabell.addCell(new Cell(2, 1).add(new Paragraph("Tab / %").setFont(font)));
        lonnslippTabell.addCell(new Cell(1, 3).add(new Paragraph("Denne Perioden").setFont(font)));
        lonnslippTabell.addCell(new Cell(1, 3).add(new Paragraph("Hittil I år").setFont(font)));
        //Rad2
        lonnslippTabell.addCell(new Cell().add(new Paragraph("Ant/Gr.lag").setFont(font)));
        lonnslippTabell.addCell(new Cell().add(new Paragraph("Sats").setFont(font)));
        lonnslippTabell.addCell(new Cell().add(new Paragraph("Beløp").setFont(font)));
        lonnslippTabell.addCell(new Cell().add(new Paragraph("Ant/Gr.lag").setFont(font)));
        lonnslippTabell.addCell(new Cell().add(new Paragraph("Beløp").setFont(font)));
        //Rad3
        timelonn(endDate).forEach(lonnslippTabell::addCell);
        //Rad4
        bruttolonn(endDate).forEach(lonnslippTabell::addCell);
        //Rad5
        skattetrekk(endDate).forEach(lonnslippTabell::addCell);
        //Rad6
        lagTomRad().forEach(lonnslippTabell::addCell);
        //Rad7
        lonnslippTabell.addCell(new Cell().add(new Paragraph("Totalt Skattetrekk")).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        IntStream.range(0, 5).forEach(i -> lonnslippTabell.addCell(lagTomCelle()));
        lonnslippTabell.addCell(new Cell().add(new Paragraph("")).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        //Rad8
        lonnslippTabell.addCell(new Cell().add(new Paragraph("Skattetrekkgrunnlag")).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        IntStream.range(0, 5).forEach(i -> lonnslippTabell.addCell(lagTomCelle()));
        lonnslippTabell.addCell(new Cell().add(new Paragraph("")).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        //Rad9
        lonnslippTabell.addCell(new Cell().add(new Paragraph("Feriepengegrunnlag")).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        IntStream.range(0, 6).forEach(i -> lonnslippTabell.addCell(lagTomCelle()));
        //lonnslippTabell.addCell(new Cell().add(new Paragraph((ansatt.getLonnId().).toString())).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        //Rad10
        lagTomRad().forEach(lonnslippTabell::addCell);
        //Rad11
        lagTomRad().forEach(lonnslippTabell::addCell);
        //Rad12
        lonnslippTabell.addCell(new Cell(1, 2).add(new Paragraph("")));
        lonnslippTabell.addCell(new Cell(1, 2).add(new Paragraph("Netto utbetalt").setFont(font)).setBorderRight(Border.NO_BORDER));
        lonnslippTabell.addCell(new Cell().add(new Paragraph(String.valueOf(kalkulerNetto())).setBorderLeft(Border.NO_BORDER)));
        lonnslippTabell.addCell(new Cell().add(new Paragraph("Utbetalt til").setFont(font)).setBorderRight(Border.NO_BORDER));
        lonnslippTabell.addCell(new Cell().add(new Paragraph("xxxx xx xxxxx")).setBorderLeft(Border.NO_BORDER));


        document.add(slippInformasjonTabell);
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(lonnslippTabell);


        document.close();
        // check if document is ok - TODO tidsplanResult.getTidsplaner().forEach(t -> t.setCalced(true));
        byte[] array = baos.toByteArray();
        return slippHistorikkService.lagreSlipp(ansatt, utbetalingsDato, brutto, (float) skatt, netto, array);
    }

    private Cell lagTomCelle() {
        return new Cell().add(new Paragraph("\n"))
                .setBorderBottom(Border.NO_BORDER)
                .setBorderTop(Border.NO_BORDER);
    }

    private List<Cell> lagTomRad() {
        return Arrays.asList(
                lagTomCelle(),
                lagTomCelle(),
                lagTomCelle(),
                lagTomCelle(),
                lagTomCelle(),
                lagTomCelle(),
                lagTomCelle()
        );
    }

    private List<Cell> timelonn(LocalDate endDate) {
        float timer = tidsplanResult.getTimer();
        timelonn = ansatt.getLonnId().getTimelonn();
        float totalTimer = tidsplanService.getTimerForAnsattHittilIAr(ansatt, endDate);

        return Arrays.asList(
                new Cell().add(new Paragraph("Timelønn")).setBorderBottom(Border.NO_BORDER),
                lagTomCelle(),
                new Cell().add(new Paragraph(formatDouble(timer))).setBorderBottom(Border.NO_BORDER),
                new Cell().add(new Paragraph(formatDouble(timelonn))).setBorderBottom(Border.NO_BORDER),
                new Cell().add(new Paragraph(formatDouble(timer * timelonn))).setBorderBottom(Border.NO_BORDER),
                new Cell().add(new Paragraph(formatDouble(totalTimer))).setBorderBottom(Border.NO_BORDER),
                new Cell().add(new Paragraph(formatDouble(totalTimer * timelonn))).setBorderBottom(Border.NO_BORDER)
        );
    }

    private List<Cell> skattetrekk(LocalDate endDate) {

        skatt = skattefratak(brutto);

        float totalTimer = (tidsplanService.getTimerForAnsattHittilIAr(ansatt, endDate));

        return Arrays.asList(
                new Cell().add(new Paragraph("Skattetrekk")).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER),
                new Cell().add(new Paragraph(30 + "")).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER),
                lagTomCelle(),
                lagTomCelle(),
                new Cell().add(new Paragraph(formatDouble(skatt))).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER),
                lagTomCelle(),
                new Cell().add(new Paragraph(formatDouble(skattefratak(totalTimer*timelonn)))).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
        );
    }

    private List<Cell> bruttolonn(LocalDate endDate) {
        brutto = timelonn * timer;

        float totalTimer = (tidsplanService.getTimerForAnsattHittilIAr(ansatt, endDate));

        return Arrays.asList(
                new Cell().add(new Paragraph("Brutto Lønn")).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER),
                lagTomCelle(),
                lagTomCelle(),
                lagTomCelle(),
                new Cell().add(new Paragraph(formatDouble(brutto))).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER),
                lagTomCelle(),
                new Cell().add(new Paragraph(formatDouble(totalTimer*timelonn))).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
        );
    }

    private double skattefratak(double lonn){
        if (70000.0/12 > lonn) return 0.0;
        return (lonn - (70000.0/12)) * 0.3;
    }

    private float kalkulerNetto(){
        netto = (float) (brutto - skatt);
        return netto;
    }

    private String formatDouble(double tall) {
        return String.format("%.2f",tall);
    }
}