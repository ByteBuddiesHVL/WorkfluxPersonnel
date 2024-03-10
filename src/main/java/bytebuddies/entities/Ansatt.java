package bytebuddies.entities;

import jakarta.persistence.*;

@Entity
@Table(schema = "Workflux")
public class Ansatt {
    @Id
    private String ansattId;

    private String navn;
    private float timelonn;
    private float stillingsprosent;
    private String stillingstype;
    private String hash;
    private String salt;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bedrift_id")
    private Bedrift bedrift_id;

    public Ansatt(){}

    public Ansatt(String ansattId, String navn, float timelonn, float stillingsprosent, String stillingstype, String hash, String salt, Bedrift bedrift_id) {
        this.ansattId = ansattId;
        this.navn = navn;
        this.timelonn = timelonn;
        this.stillingsprosent = stillingsprosent;
        this.stillingstype = stillingstype;
        this.hash = hash;
        this.salt = salt;
        this.bedrift_id = bedrift_id;
    }

    public String getAnsatt_id() {
        return ansattId;
    }

    public void setAnsatt_id(String ansatt_id) {
        this.ansattId = ansatt_id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public float getTimelonn() {
        return timelonn;
    }

    public void setTimelonn(float timelonn) {
        this.timelonn = timelonn;
    }

    public float getStillingsprosent() {
        return stillingsprosent;
    }

    public void setStillingsprosent(float stillingsprosent) {
        this.stillingsprosent = stillingsprosent;
    }

    public String getStillingstype() {
        return stillingstype;
    }

    public void setStillingstype(String stillingstype) {
        this.stillingstype = stillingstype;
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

    public Bedrift getBedrift_id() {
        return bedrift_id;
    }

    public void setBedrift_id(Bedrift bedrift_id) {
        this.bedrift_id = bedrift_id;
    }
}