package bytebuddies.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(schema = "Workflux",name = "slipphistorikk")
public class SlippHistorikk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lonnsslipp_id")
    private Integer lonnsslippId;

    @ManyToOne
    @JoinColumn(name = "ansatt_id")
    private Ansatt ansattId;
    private LocalDate dato;
    private Float brutto;
    private Float skatt;
    private Float netto;
    private Float timer;
    private Float timelonn;
    @Column(name = "file_data")
    private byte[] fileData;

    public SlippHistorikk(Ansatt ansattId, LocalDate dato, Float brutto, Float skatt, Float netto, Float timer, Float timelonn, byte[] fileData) {
        this.ansattId = ansattId;
        this.dato = dato;
        this.brutto = brutto;
        this.skatt = skatt;
        this.netto = netto;
        this.timer = timer;
        this.timelonn = timelonn;
        this.fileData = fileData;
    }



    public SlippHistorikk() {}

    public Integer getLonnsslippId() {
        return lonnsslippId;
    }

    public void setLonnsslippId(Integer lonnsslippId) {
        this.lonnsslippId = lonnsslippId;
    }

    public Ansatt getAnsattId() {
        return ansattId;
    }

    public void setAnsattId(Ansatt ansattId) {
        this.ansattId = ansattId;
    }

    public LocalDate getDato() {
        return dato;
    }

    public void setDato(LocalDate dato) {
        this.dato = dato;
    }

    public Float getBrutto() {
        return brutto;
    }

    public void setBrutto(Float brutto) {
        this.brutto = brutto;
    }

    public Float getSkatt() {
        return skatt;
    }

    public void setSkatt(Float skatt) {
        this.skatt = skatt;
    }

    public Float getNetto() {
        return netto;
    }

    public void setNetto(Float netto) {
        this.netto = netto;
    }

    public Float getTimer() {
        return timer;
    }

    public void setTimer(Float timer) {
        this.timer = timer;
    }

    public Float getTimelonn() {
        return timelonn;
    }

    public void setTimelonn(Float timelonn) {
        this.timelonn = timelonn;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
