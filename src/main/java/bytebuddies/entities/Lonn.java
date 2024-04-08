package bytebuddies.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

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

    public Lonn(Float timelonn, Float arslonn, LocalDate virkningsdato) {
        this.timelonn = timelonn;
        this.arslonn = arslonn;
        this.virkningsdato = virkningsdato;
    }

    public Lonn() {}

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
