package bytebuddies.entities;

import bytebuddies.embeddable.AnsattId;
import bytebuddies.embeddable.Passord;
import jakarta.persistence.*;

@Entity
@Table(schema = "Workflux")
public class Ansatt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ansatt_id")
    private Integer ansattId;
    private String brukernavn;
    @ManyToOne
    @JoinColumn(name = "bedrift_id")
    private Bedrift bedriftId;
    @Embedded
    private Passord passord;
    private String fornavn;
    private String etternavn;
    private String telefonnummer;
    private String epost;
    @ManyToOne
    @JoinColumn(name = "adresse_id")
    private Adresse adresseId;
    @Column(name = "is_active")
    private boolean isActive;
    private float stillingsprosent;
    private String stillingstype;

    public Ansatt(Bedrift bedriftId, Passord passord, String fornavn, String etternavn, String telefonnummer, String epost, Adresse adresseId, boolean isActive, float stillingsprosent, String stillingstype) {
        this.bedriftId = bedriftId;
        this.passord = passord;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.telefonnummer = telefonnummer;
        this.epost = epost;
        this.adresseId = adresseId;
        this.isActive = isActive;
        this.stillingsprosent = stillingsprosent;
        this.stillingstype = stillingstype;
    }

    public Ansatt(){}

    public Integer getAnsattId() {
        return ansattId;
    }

    public void setAnsattId(Integer ansattId) {
        this.ansattId = ansattId;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public Bedrift getBedriftId() {
        return bedriftId;
    }

    public void setBedriftId(Bedrift bedriftId) {
        this.bedriftId = bedriftId;
    }

    public Passord getPassord() {
        return passord;
    }

    public void setPassord(Passord passord) {
        this.passord = passord;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public Adresse getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Adresse adresseId) {
        this.adresseId = adresseId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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
}