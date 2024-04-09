package bytebuddies.embeddable;

import bytebuddies.entities.Bedrift;
import jakarta.persistence.*;

import java.io.Serializable;

/**
 * Embeddable klasse som representerer den sammensatte nøkkelen for en Ansatt-entitet.
 */
@Embeddable
public class AnsattId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "bedrift_id")
    private Bedrift bedriftId;

    @Column(name = "ansatt_id")
    private Integer ansattId;

    /**
     * Konstruktør som oppretter en ny AnsattId-instans med angitt bedriftsobjekt.
     *
     * @param bedriftId Bedriftsobjektet til den ansatte.
     */
    public AnsattId(Bedrift bedriftId) {
        this.bedriftId = bedriftId;
    }

    /**
     * Tom konstruktør for AnsattId.
     */
    public AnsattId() {
    }

    //Getter- og setter-metoder
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
