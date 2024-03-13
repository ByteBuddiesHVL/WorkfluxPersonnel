package bytebuddies.entities;

import jakarta.persistence.*;

@Entity
@Table(schema = "Workflux")
public class Dev {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer devId;
    @Column(unique = true)
    private String brukernavn;
    private String hash;
    private String salt;

    public Dev(String brukernavn, String hash, String salt) {
        this.brukernavn = brukernavn;
        this.hash = hash;
        this.salt = salt;
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

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
