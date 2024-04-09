package bytebuddies.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Representerer lønnsinformasjon for en ansatt i Workflux-systemet.
 * Inkluderer informasjon om enten time- eller årslønn, samt datoen lønnen trer i kraft.
 */
@Entity
@Table(schema = "Workflux")
public class Lonn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lonn_id")
    private Integer lonnId;
    private Float timelonn; //kan være null (da er arslonn ikke null)
    private Float arslonn; //kan være null (da er timelonn ikke null)
    private LocalDate virkningsdato;

    /**
     * Oppretter en ny lønnsinstans med spesifisert time- eller årslønn og virkningsdato.
     *
     * @param timelonn Timebetaling for en ansatt. Kan være {@code null} hvis {@code arslonn} er satt.
     * @param arslonn Årslønn for en ansatt. Kan være {@code null} hvis {@code timelonn} er satt.
     * @param virkningsdato Datoen den nye lønnen trer i kraft.
     */
    public Lonn(Float timelonn, Float arslonn, LocalDate virkningsdato) {
        this.timelonn = timelonn;
        this.arslonn = arslonn;
        this.virkningsdato = virkningsdato;
    }

    /**
     * Standard tom konstruktør.
     */
    public Lonn() {}

    //Getter- og setter-metoder
    public Integer getLonnId() {
        return lonnId;
    }

    public void setLonnId(Integer lonnId) {
        this.lonnId = lonnId;
    }

    public Float getTimelonn() {
        return timelonn;
    }

    public void setTimelonn(Float timelonn) {
        this.timelonn = timelonn;
    }

    public Float getArslonn() {
        return arslonn;
    }

    public void setArslonn(Float arslonn) {
        this.arslonn = arslonn;
    }

    public LocalDate getVirkningsdato() {
        return virkningsdato;
    }

    public void setVirkningsdato(LocalDate virkningsdato) {
        this.virkningsdato = virkningsdato;
    }
}
