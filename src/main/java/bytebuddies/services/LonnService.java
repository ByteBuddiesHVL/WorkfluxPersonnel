package bytebuddies.services;

import bytebuddies.entities.Admin;
import bytebuddies.entities.Ansatt;
import bytebuddies.entities.Lonn;
import bytebuddies.repositories.AnsattRepository;
import bytebuddies.repositories.LonnRepository;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

@Service
public class LonnService {

    @Autowired
    private LonnRepository lonnRepository;

    @Autowired
    private AnsattRepository ansattRepository;

    public Optional<Lonn> finnTimelonnForAnsatt(Ansatt ansatt) {
        return lonnRepository.getTimelonnByAnsattId(ansatt);
    }

    public Optional<Lonn> finnArslonnForAnsatt(Ansatt ansatt) {
        return lonnRepository.getArslonnByAnsattId(ansatt);
    }

    public byte[] genererLonnslipp(Integer ansattId) throws IOException {

        Ansatt ansatt = ansattRepository.findById(ansattId).orElseThrow();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        float[] columnWidths1 = {170, 170};
        Table slippInformasjonTabell = new Table(UnitValue.createPointArray(columnWidths1));
        //Rad1
        slippInformasjonTabell.addCell(new Cell(1, 2).add(new Paragraph("Lønnslipp Informasjon")).setFont(font).setTextAlignment(TextAlignment.CENTER));
        //Rad2
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph("Lønnsmottaker")).setFont(font).setBorder(Border.NO_BORDER).setBorderLeft(new SolidBorder(1)));
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph(ansatt.getFornavn() + ansatt.getEtternavn())).setBorder(Border.NO_BORDER).setBorderRight(new SolidBorder(1)));
        //Rad3
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph("Adresse")).setFont(font).setBorder(Border.NO_BORDER).setBorderLeft(new SolidBorder(1)));
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER).setBorderRight(new SolidBorder(1)));
        //Rad4
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph("AnsattId")).setFont(font).setBorder(Border.NO_BORDER).setBorderLeft(new SolidBorder(1)));
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph(ansatt.getAnsattId().toString())).setBorder(Border.NO_BORDER).setBorderRight(new SolidBorder(1)));
        //Rad6
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph("Firmanavn / Avsender")).setFont(font).setBorder(Border.NO_BORDER).setBorderLeft(new SolidBorder(1)));
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER).setBorderRight(new SolidBorder(1)));
        //Rad7
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph("LønnsslippId")).setFont(font).setBorder(Border.NO_BORDER).setBorderLeft(new SolidBorder(1)));
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER).setBorderRight(new SolidBorder(1)));
        //Rad8
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph("Dato utbetalt")).setFont(font).setBorder(Border.NO_BORDER).setBorderLeft(new SolidBorder(1)).setBorderBottom(new SolidBorder(1)));
        slippInformasjonTabell.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER).setBorderRight(new SolidBorder(1)).setBorderBottom(new SolidBorder(1)));


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
        lonnslippTabell.addCell(new Cell().add(new Paragraph("Timelønn")).setBorderBottom(Border.NO_BORDER));
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(new Cell().add(new Paragraph("")).setBorderBottom(Border.NO_BORDER));
        lonnslippTabell.addCell(new Cell().add(new Paragraph(finnTimelonnForAnsatt(ansatt).toString())).setBorderBottom(Border.NO_BORDER));
        lonnslippTabell.addCell(new Cell().add(new Paragraph("")).setBorderBottom(Border.NO_BORDER));
        lonnslippTabell.addCell(new Cell().add(new Paragraph("")).setBorderBottom(Border.NO_BORDER));
        lonnslippTabell.addCell(new Cell().add(new Paragraph("")).setBorderBottom(Border.NO_BORDER));
        //Rad4
        lonnslippTabell.addCell(new Cell().add(new Paragraph("Skattetrekk")).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        lonnslippTabell.addCell(new Cell().add(new Paragraph("")).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(new Cell().add(new Paragraph("")).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(new Cell().add(new Paragraph("")).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        //Rad5
        lonnslippTabell.addCell(new Cell().add(new Paragraph("Brutto Lønn")).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(new Cell().add(new Paragraph("")).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(new Cell().add(new Paragraph(finnArslonnForAnsatt(ansatt).toString())).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        //Rad6
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        //Rad7
        lonnslippTabell.addCell(new Cell().add(new Paragraph("Totalt Skattetrekk")).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(new Cell().add(new Paragraph("")).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        //Rad8
        lonnslippTabell.addCell(new Cell().add(new Paragraph("Skattetrekkgrunnlag")).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(new Cell().add(new Paragraph("")).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        //Rad9
        lonnslippTabell.addCell(new Cell().add(new Paragraph("Feriepengegrunnlag")).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(new Cell().add(new Paragraph(finnArslonnForAnsatt(ansatt).toString())).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        //Rad10
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        lonnslippTabell.addCell(lagTomCelle());
        //Rad11
        lonnslippTabell.addCell(new Cell(1, 2).add(new Paragraph("")));
        lonnslippTabell.addCell(new Cell(1, 2).add(new Paragraph("Netto utbetalt").setFont(font)).setBorderRight(Border.NO_BORDER));
        lonnslippTabell.addCell(new Cell().add(new Paragraph("")).setBorderLeft(Border.NO_BORDER));
        lonnslippTabell.addCell(new Cell().add(new Paragraph("Utbetalt til").setFont(font)).setBorderRight(Border.NO_BORDER));
        lonnslippTabell.addCell(new Cell().add(new Paragraph("")).setBorderLeft(Border.NO_BORDER));


        document.add(slippInformasjonTabell);
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(lonnslippTabell);


        document.close();

        return baos.toByteArray();
    }

    private Cell lagTomCelle() {
        return new Cell().add(new Paragraph("\n"))
                .setBorderBottom(Border.NO_BORDER)
                .setBorderTop(Border.NO_BORDER);
    }

}
