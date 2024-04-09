package bytebuddies.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Representerer skattetrekkopplysninger for en ansatt i Workflux-systemet.
 * Inkluderer detaljer som skattetabell, prosenttrekk, og virkningsdato for skattetrekket.
 */
@Entity
@Table(schema = "Workflux")
public class Skatt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skatt_id")
    private Integer skattId;
    @ManyToOne
    @JoinColumn(name = "ansatt_id")
    private Ansatt ansattId;
    private String tabell; // om ansatt har tabelltrekk
    private float prosent; // om ansatt har prosenttrekk
    private LocalDate virkningsdato;

    /**
     * Oppretter en ny instans av Skatt med tilknyttet ansatt, skattetrekk informasjon og virkningsdato.
     *
     * @param ansattId        Den ansatte skattetrekket gjelder for.
     * @param tabell          Skattetabellen som brukes, null hvis prosenttrekk er brukt.
     * @param prosent         Prosentandelen som brukes for prosenttrekk, 0 hvis tabelltrekk er brukt.
     * @param virkningsdato   Datoen skattetrekket trer i kraft.
     */
    public Skatt(Ansatt ansattId, String tabell, float prosent, LocalDate virkningsdato) {
        this.ansattId = ansattId;
        this.tabell = tabell;
        this.prosent = prosent;
        this.virkningsdato = virkningsdato;
    }
    /**
     * Standard tom konstruktør.
     */
    public Skatt() {}

    //Getter- og setter-metoder

    public Integer getSkattId() {
        return skattId;
    }

    public void setSkattId(Integer skattId) {
        this.skattId = skattId;
    }

    public Ansatt getAnsattId() {
        return ansattId;
    }

    public void setAnsattId(Ansatt ansattId) {
        this.ansattId = ansattId;
    }

    public String getTabell() {
        return tabell;
    }

    public void setTabell(String tabell) {
        this.tabell = tabell;
    }

    public float getProsent() {
        return prosent;
    }

    public void setProsent(float prosent) {
        this.prosent = prosent;
    }

    public LocalDate getVirkningsdato() {
        return virkningsdato;
    }

    public void setVirkningsdato(LocalDate virkningsdato) {
        this.virkningsdato = virkningsdato;
    }
}
