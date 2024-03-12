package bytebuddies.embeddable;

import bytebuddies.entities.Bedrift;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class AnsattId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "bedriftId")
    private Bedrift bedriftId;
    private String ansattId;

    public AnsattId(Bedrift bedriftId, String ansattId) {
        this.bedriftId = bedriftId;
        this.ansattId = ansattId;
    }

    public AnsattId() {
    }

    public Bedrift getBedriftId() {
        return bedriftId;
    }

    public void setBedriftId(Bedrift bedriftId) {
        this.bedriftId = bedriftId;
    }

    public String getAnsattId() {
        return ansattId;
    }

    public void setAnsattId(String ansattId) {
        this.ansattId = ansattId;
    }
}
