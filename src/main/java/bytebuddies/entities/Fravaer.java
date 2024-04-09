package bytebuddies.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Representerer et fravær for en ansatt i Workflux-systemet.
 * Inneholder informasjon om den ansatte fraværet gjelder, typen fravær, forklaring, samt start- og sluttdato.
 */
@Entity
@Table(schema = "Workflux")
public class Fravaer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fravaer_id")
    private Integer fravaerId;
    @ManyToOne
    @JoinColumn(name = "ansatt_id")
    private Ansatt ansattId;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Fravaerstype typeId;
    private String forklaring;
    private LocalDate startdato;
    private LocalDate sluttdato;

    /**
     * Oppretter en ny instans av {@code Fravaer}.
     *
     * @param ansattId    Den ansatte som fraværet gjelder.
     * @param typeId      Typen fravær registrert.
     * @param forklaring  Forklaring eller detaljer om fraværet.
     * @param startdato   Startdatoen for fraværsperioden.
     * @param sluttdato   Sluttdatoen for fraværsperioden.
     */
    public Fravaer(Ansatt ansattId, Fravaerstype typeId, String forklaring, LocalDate startdato, LocalDate sluttdato) {
        this.ansattId = ansattId;
        this.typeId = typeId;
        this.forklaring = forklaring;
        this.startdato = startdato;
        this.sluttdato = sluttdato;
    }

    /**
     * Standard tom konstruktør.
     */
    public Fravaer() {}

    //Getter- og setter-metoder
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
