package bytebuddies.entities;

import jakarta.persistence.*;

@Entity
@Table(schema = "Workflux")
public class Tidsplantype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tidsplantype_id")
    private Integer tidsplantypeId;

    private String type;

    @ManyToOne
    @JoinColumn(name = "bedrift_id")
    private Bedrift bedriftId;

    public Tidsplantype(String type, Bedrift bedriftId) {
        this.type = type;
        this.bedriftId = bedriftId;
    }

    public Tidsplantype() {
    }

    public Integer getTidsplantypeId() {
        return tidsplantypeId;
    }

    public void setTidsplantypeId(Integer tidsplantypeId) {
        this.tidsplantypeId = tidsplantypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Bedrift getBedriftId() {
        return bedriftId;
    }

    public void setBedriftId(Bedrift bedriftId) {
        this.bedriftId = bedriftId;
    }

    public String toString() {
        return "[\"" +
                tidsplantypeId + "\",\"" +
                type + "\"]";
    }

}
