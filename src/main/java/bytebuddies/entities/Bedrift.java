package bytebuddies.entities;

import jakarta.persistence.*;

/**
 * Representerer en bedrift i Workflux-systemet.
 * En bedrift er identifisert ved en unik ID og kan ha et navn og en unik forkortelse.
 */
@Entity
@Table(schema="Workflux")
public class Bedrift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bedrift_id")
    private Integer bedriftId;
    private String navn;
    @Column(unique = true)
    private String forkortelse;

    /**
     * Standard tom konstruktør.
     */
    public Bedrift(){}

    /**
     * Oppretter en ny bedrift med spesifisert navn og forkortelse.
     *
     * @param navn Bedriftens navn.
     * @param forkortelse Bedriftens unike forkortelse.
     */
    public Bedrift(String navn, String forkortelse) {
        this.navn = navn;
        this.forkortelse = forkortelse;
    }

    //Getter- og setter-metoder
    public int getBedriftId() {
        return bedriftId;
    }

    public void setBedriftId(int bedriftId) {
        this.bedriftId = bedriftId;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getForkortelse() {
        return forkortelse;
    }

    public void setForkortelse(String forkortelse) {
        this.forkortelse = forkortelse;
    }
}

