package bytebuddies.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Lonn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lonnId;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "bedriftId",referencedColumnName = "bedriftId"),
            @JoinColumn(name = "ansattId",referencedColumnName = "ansattId")
    })
    private Ansatt ansattId;

    private float timelonn; //kan være null (da er arslonn ikke null)
    private float arslonn; //kan være null (da er timelonn ikke null)
    private LocalDate virkningsdato;

    public Lonn(Ansatt ansattId, float timelonn, float arslonn, LocalDate virkningsdato) {
        this.ansattId = ansattId;
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

    public Ansatt getAnsattId() {
        return ansattId;
    }

    public void setAnsattId(Ansatt ansattId) {
        this.ansattId = ansattId;
    }

    public float getTimelonn() {
        return timelonn;
    }

    public void setTimelonn(float timelonn) {
        this.timelonn = timelonn;
    }

    public float getArslonn() {
        return arslonn;
    }

    public void setArslonn(float arslonn) {
        this.arslonn = arslonn;
    }

    public LocalDate getVirkningsdato() {
        return virkningsdato;
    }

    public void setVirkningsdato(LocalDate virkningsdato) {
        this.virkningsdato = virkningsdato;
    }
}
