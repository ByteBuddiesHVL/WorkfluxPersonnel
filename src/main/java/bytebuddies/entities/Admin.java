package bytebuddies.entities;

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
    private String hash;
    private String salt;

    public Admin(Bedrift bedriftId, String brukernavn, String hash, String salt) {
        this.bedriftId = bedriftId;
        this.brukernavn = brukernavn;
        this.hash = hash;
        this.salt = salt;
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
