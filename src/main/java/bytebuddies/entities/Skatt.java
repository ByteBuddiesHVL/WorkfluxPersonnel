package bytebuddies.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(schema = "Workflux")
public class Skatt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer skattId;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "bedriftId",referencedColumnName = "bedriftId"),
            @JoinColumn(name = "ansattId",referencedColumnName = "ansattId")
    })
    private Ansatt ansattId;
    private String tabell; // om ansatt har tabelltrekk
    private float prosent; // om ansatt har prosenttrekk
    private LocalDate virkningsdato;

    public Skatt(Ansatt ansattId, String tabell, float prosent, LocalDate virkningsdato) {
        this.ansattId = ansattId;
        this.tabell = tabell;
        this.prosent = prosent;
        this.virkningsdato = virkningsdato;
    }
    public Skatt() {}

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
