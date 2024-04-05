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

    public Tidsplantype(Integer tidsplantypeId, String type) {
        this.tidsplantypeId = tidsplantypeId;
        this.type = type;
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
}
