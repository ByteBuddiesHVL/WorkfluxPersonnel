package bytebuddies.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(schema = "Workflux")
public class Tidsplan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tidsplan_id")
    private int tidsplanId;

    @ManyToOne
    @JoinColumn(name = "ansatt_id")
    private Ansatt ansattId;

    private LocalDateTime starttid;
    private LocalDateTime sluttid;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Tidsplantype typeId;

    public Tidsplan(Ansatt ansattId, LocalDateTime starttid, LocalDateTime sluttid, Tidsplantype typeId) {
        this.ansattId = ansattId;
        this.starttid = starttid;
        this.sluttid = sluttid;
        this.typeId = typeId;
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

    public Tidsplantype getTypeId() {
        return typeId;
    }

    public void setTypeId(Tidsplantype type_id) {
        this.typeId = type_id;
    }
}
