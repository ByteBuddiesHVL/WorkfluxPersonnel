package bytebuddies.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Locale;

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

    @Column(name = "is_calced")
    private Boolean isCalced;

    public Tidsplan(Ansatt ansattId, LocalDateTime starttid, LocalDateTime sluttid, Tidsplantype typeId, Boolean isCalced) {
        this.ansattId = ansattId;
        this.starttid = starttid;
        this.sluttid = sluttid;
        this.typeId = typeId;
        this.isCalced = isCalced;
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

    public Boolean getCalced() {
        return isCalced;
    }

    public void setCalced(Boolean calced) {
        isCalced = calced;
    }

    public String toString() {
        return "[\"" +
                tidsplanId + "\"," +
                ansattId.toString() + ",\"" +
                starttid.toString() + "\",\"" +
                sluttid.toString() + "\",\"" +
                typeId.getTidsplantypeId() + "\",\"" +
                typeId.getType() + "\"]";
    }
}
