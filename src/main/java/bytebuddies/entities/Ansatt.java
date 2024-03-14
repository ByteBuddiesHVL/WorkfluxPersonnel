package bytebuddies.entities;

import bytebuddies.embeddable.AnsattId;
import bytebuddies.embeddable.Passord;
import jakarta.persistence.*;

@Entity
@Table(schema = "Workflux")
public class Ansatt {
    @EmbeddedId
    private AnsattId ansattId;
    @Embedded
    private Passord passord;
    private String fornavn;
    private String etternavn;
    private String telefonnummer;
    private String epost;
    @ManyToOne
    @JoinColumn(name = "adresseId")
    private Adresse adresseId;
    private boolean isActive;
    private float stillingsprosent;
    private String stillingstype;

    public Ansatt(AnsattId ansattId, Passord passord, String fornavn, String etternavn, String telefonnummer, String epost, Adresse adresseId, boolean isActive, float stillingsprosent, String stillingstype) {
        this.ansattId = ansattId;
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

    public AnsattId getAnsattId() {
        return ansattId;
    }

    public void setAnsattId(AnsattId ansattId) {
        this.ansattId = ansattId;
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