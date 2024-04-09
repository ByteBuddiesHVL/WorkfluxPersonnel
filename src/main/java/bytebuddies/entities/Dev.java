package bytebuddies.entities;

import bytebuddies.embeddable.Passord;
import jakarta.persistence.*;

/**
 * Representerer en utvikler (Dev) i Workflux-systemet.
 * Denne klassen inneholder informasjon om utviklerens unike identifikator, brukernavn og passord.
 */
@Entity
@Table(schema = "Workflux")
public class Dev {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dev_id")
    private Integer devId;
    @Column(unique = true)
    private String brukernavn;
    @Embedded
    private Passord passord;

    /**
     * Oppretter en ny {@code Dev}-instans med et spesifisert brukernavn og passord.
     * Denne konstruktøren brukes for å lage nye utviklerkontoer.
     *
     * @param brukernavn Brukernavnet til utvikleren, som må være unikt.
     * @param passord    Utviklerens passord, representert ved {@link Passord}.
     */
    public Dev(String brukernavn, Passord passord) {
        this.brukernavn = brukernavn;
        this.passord = passord;
    }

    /**
     * Standard tom konstruktør.
     */
    public Dev() {}

    //Getter- og setter-metoder
    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public Passord getPassord() {
        return passord;
    }

    public void setPassord(Passord passord) {
        this.passord = passord;
    }
}
