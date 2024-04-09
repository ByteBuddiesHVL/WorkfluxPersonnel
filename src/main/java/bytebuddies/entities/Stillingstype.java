package bytebuddies.entities;

import jakarta.persistence.*;

/**
 * Representerer en stillingstype tilknyttet en spesifikk bedrift i Workflux-systemet.
 * Denne klassen holder informasjon om stillingstypens unike identifikator, navnet på stillingstypen,
 * og en referanse til den tilknyttede bedriften.
 */
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

    /**
     * Oppretter en ny {@code Stillingstype} med en spesifisert identifikator, navn på stillingstypen,
     * og en referanse til den tilknyttede bedriften.
     *
     * @param stillingstypeId Unik identifikator for stillingstypen.
     * @param stillingstype   Navnet på stillingstypen.
     * @param bedriftId       Referansen til den tilknyttede bedriften.
     */
    public Stillingstype(Integer stillingstypeId, String stillingstype, Bedrift bedriftId) {
        this.stillingstypeId = stillingstypeId;
        this.stillingstype = stillingstype;
        this.bedriftId = bedriftId;
    }

    /**
     * Standard tom konstruktør.
     */
    public Stillingstype() {
    }

    //Getter- og setter-metoder
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
