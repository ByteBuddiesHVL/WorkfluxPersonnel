package bytebuddies.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(schema = "Workflux")
public class SlippHistorikk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lonnsslippId;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "bedriftId",referencedColumnName = "bedriftId"),
            @JoinColumn(name = "ansattId",referencedColumnName = "ansattId")
    })
    private Ansatt ansattId;
    private LocalDate dato;
    private float brutto;
    private float skatt;
    private float netto;

    public SlippHistorikk(Ansatt ansattId, LocalDate dato, float brutto, float skatt, float netto) {
        this.ansattId = ansattId;
        this.dato = dato;
        this.brutto = brutto;
        this.skatt = skatt;
        this.netto = netto;
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

    public float getBrutto() {
        return brutto;
    }

    public void setBrutto(float brutto) {
        this.brutto = brutto;
    }

    public float getSkatt() {
        return skatt;
    }

    public void setSkatt(float skatt) {
        this.skatt = skatt;
    }

    public float getNetto() {
        return netto;
    }

    public void setNetto(float netto) {
        this.netto = netto;
    }
}
