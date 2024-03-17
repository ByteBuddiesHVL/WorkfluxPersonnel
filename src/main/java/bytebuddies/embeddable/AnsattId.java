package bytebuddies.embeddable;

import bytebuddies.entities.Bedrift;
import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class AnsattId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "bedrift_id")
    private Bedrift bedriftId;

    @Column(name = "ansatt_id")
    private Integer ansattId;

    public AnsattId(Bedrift bedriftId) {
        this.bedriftId = bedriftId;
    }

    public AnsattId() {
    }

    public Bedrift getBedriftId() {
        return bedriftId;
    }

    public void setBedriftId(Bedrift bedriftId) {
        this.bedriftId = bedriftId;
    }

    public Integer getAnsattId() {
        return ansattId;
    }

    public void setAnsattId(Integer ansattId) {
        this.ansattId = ansattId;
    }
}
