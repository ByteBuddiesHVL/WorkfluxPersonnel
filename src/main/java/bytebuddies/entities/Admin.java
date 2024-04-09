package bytebuddies.entities;

import bytebuddies.embeddable.Passord;
import jakarta.persistence.*;

/**
 * Representerer en administrator for en bedrift i systemet.
 * En {@code Admin} er knyttet til en spesifikk {@link Bedrift} og har unike påloggingsdetaljer.
 */
@Entity
@Table(schema = "Workflux")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer adminId;
    @ManyToOne
    @JoinColumn(name = "bedrift_id")
    private Bedrift bedriftId;
    @Column(unique = true)
    private String brukernavn;
    @Embedded
    private Passord passord;

    /**
     * Oppretter en ny {@code Admin} med en tilknyttet bedrift, brukernavn, og passord.
     *
     * @param bedriftId   bedriften administratoren tilhører
     * @param brukernavn  administratorens unike brukernavn
     * @param passord     administratorens passord
     */
    public Admin(Bedrift bedriftId, String brukernavn, Passord passord) {
        this.bedriftId = bedriftId;
        this.brukernavn = brukernavn;
        this.passord = passord;
    }

    /**
     * Standard tom konstruktør.
     */
    public Admin() {}

    //Getter- og setter-metoder
    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Bedrift getBedriftId() {
        return bedriftId;
    }

    public void setBedriftId(Bedrift bedriftId) {
        this.bedriftId = bedriftId;
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
