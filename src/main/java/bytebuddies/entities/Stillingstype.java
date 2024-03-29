package bytebuddies.entities;

import jakarta.persistence.*;

@Entity
@Table(schema = "Workflux")
public class Stillingstype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stillingstype_id")
    private Integer stillingstypeId;
    private String stillingstype;
    @ManyToOne
    @JoinColumn(name = "bedrift_id")
    private Bedrift bedriftId;

    public Stillingstype(Integer stillingstypeId, String stillingstype, Bedrift bedriftId) {
        this.stillingstypeId = stillingstypeId;
        this.stillingstype = stillingstype;
        this.bedriftId = bedriftId;
    }

    public Stillingstype() {
    }

    public Integer getStillingstypeId() {
        return stillingstypeId;
    }

    public void setStillingstypeId(Integer stillingstypeId) {
        this.stillingstypeId = stillingstypeId;
    }

    public String getStillingstype() {
        return stillingstype;
    }

    public void setStillingstype(String stillingstype) {
        this.stillingstype = stillingstype;
    }

    public Bedrift getBedriftId() {
        return bedriftId;
    }

    public void setBedriftId(Bedrift bedriftId) {
        this.bedriftId = bedriftId;
    }
}
