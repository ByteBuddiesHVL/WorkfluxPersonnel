package bytebuddies.entities;

import bytebuddies.embeddable.Passord;
import jakarta.persistence.*;

@Entity
@Table(schema = "Workflux")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;
    @ManyToOne
    @JoinColumn(name = "bedriftId")
    private Bedrift bedriftId;
    @Column(unique = true)
    private String brukernavn;
    @Embedded
    private Passord passord;

    public Admin(Bedrift bedriftId, String brukernavn, Passord passord) {
        this.bedriftId = bedriftId;
        this.brukernavn = brukernavn;
        this.passord = passord;
    }

    public Admin() {}

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
