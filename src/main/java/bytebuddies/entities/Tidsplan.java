package bytebuddies.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(schema = "Workflux")
public class Tidsplan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tidsplanId;

    @ManyToOne
    @JoinColumn(name = "ansattId")
    private Ansatt ansattId;

    private LocalDateTime starttid;
    private LocalDateTime sluttid;

    public Tidsplan(Ansatt ansattId, LocalDateTime starttid, LocalDateTime sluttid) {
        this.ansattId = ansattId;
        this.starttid = starttid;
        this.sluttid = sluttid;
    }

    public Tidsplan() {}

    public int getTidsplanId() {
        return tidsplanId;
    }

    public void setTidsplanId(int tidsplanId) {
        this.tidsplanId = tidsplanId;
    }

    public Ansatt getAnsattId() {
        return ansattId;
    }

    public void setAnsattId(Ansatt ansattId) {
        this.ansattId = ansattId;
    }

    public LocalDateTime getStarttid() {
        return starttid;
    }

    public void setStarttid(LocalDateTime starttid) {
        this.starttid = starttid;
    }

    public LocalDateTime getSluttid() {
        return sluttid;
    }

    public void setSluttid(LocalDateTime sluttid) {
        this.sluttid = sluttid;
    }
}
