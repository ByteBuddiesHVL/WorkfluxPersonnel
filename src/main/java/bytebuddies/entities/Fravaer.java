package bytebuddies.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Fravaer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fravaerId;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "bedriftId",referencedColumnName = "bedriftId"),
            @JoinColumn(name = "ansattId",referencedColumnName = "ansattId")
    })
    private Ansatt ansattId;
    @ManyToOne
    @JoinColumn(name = "typeId")
    private Fravaerstype typeId;
    private String forklaring;
    private LocalDate startdato;
    private LocalDate sluttdato;

    public Fravaer(Ansatt ansattId, Fravaerstype typeId, String forklaring, LocalDate startdato, LocalDate sluttdato) {
        this.ansattId = ansattId;
        this.typeId = typeId;
        this.forklaring = forklaring;
        this.startdato = startdato;
        this.sluttdato = sluttdato;
    }

    public Fravaer() {}

    public Integer getFravaerId() {
        return fravaerId;
    }

    public void setFravaerId(Integer fravaerId) {
        this.fravaerId = fravaerId;
    }

    public Ansatt getAnsattId() {
        return ansattId;
    }

    public void setAnsattId(Ansatt ansattId) {
        this.ansattId = ansattId;
    }

    public Fravaerstype getTypeId() {
        return typeId;
    }

    public void setTypeId(Fravaerstype typeId) {
        this.typeId = typeId;
    }

    public String getForklaring() {
        return forklaring;
    }

    public void setForklaring(String forklaring) {
        this.forklaring = forklaring;
    }

    public LocalDate getStartdato() {
        return startdato;
    }

    public void setStartdato(LocalDate startdato) {
        this.startdato = startdato;
    }

    public LocalDate getSluttdato() {
        return sluttdato;
    }

    public void setSluttdato(LocalDate sluttdato) {
        this.sluttdato = sluttdato;
    }
}
