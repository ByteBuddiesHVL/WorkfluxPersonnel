package bytebuddies.entities;

import bytebuddies.embeddable.Passord;
import jakarta.persistence.*;

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

    public Dev(String brukernavn, Passord passord) {
        this.brukernavn = brukernavn;
        this.passord = passord;
    }

    public Dev() {}

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
