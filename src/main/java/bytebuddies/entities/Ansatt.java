package bytebuddies.entities;

import bytebuddies.embeddable.Passord;
import jakarta.persistence.*;

import java.util.Locale;

/**
 * Representerer en ansatt innen Workflux-systemet.
 * Inneholder informasjon som identifiserer en ansatt, samt deres kontaktinformasjon,
 * arbeidsdetaljer og tilknytning til en spesifikk bedrift.
 */
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
    @OneToOne
    @JoinColumn(name = "lonn_id")
    private Lonn lonnId;
    private float stillingsprosent;
    @ManyToOne
    @JoinColumn(name = "stillingstype_id")
    private Stillingstype stillingstypeId;

    /**
     * Standard tom konstruktør.
     */
    public Ansatt(){}

    /**
     * Konstruerer en ny ansatt med detaljert informasjon.
     *
     * @param bedriftId         Bedriften ansatt tilhører.
     * @param passord           Passord for ansattes brukerkonto.
     * @param fornavn           Ansatte fornavn.
     * @param etternavn         Ansatte etternavn.
     * @param telefonnummer     Ansatte telefonnummer.
     * @param epost             Ansatte epostadresse.
     * @param adresseId         Adresseobjekt som representerer ansattes bosted.
     * @param isActive          Status som indikerer om ansatte er aktiv.
     * @param lonnId            Lønn objekt som representerer ansattes lønn.
     * @param stillingsprosent  Prosentdel av en full stilling som ansatte innehar.
     * @param stillingstypeId   Stillingstype objekt som representerer ansattes rolle i bedriften.
     */
    public Ansatt(Bedrift bedriftId, Passord passord, String fornavn, String etternavn, String telefonnummer, String epost, Adresse adresseId, boolean isActive, Lonn lonnId, float stillingsprosent, Stillingstype stillingstypeId) {
        this.bedriftId = bedriftId;
        this.passord = passord;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.telefonnummer = telefonnummer;
        this.epost = epost;
        this.adresseId = adresseId;
        this.isActive = isActive;
        this.lonnId = lonnId;
        this.stillingsprosent = stillingsprosent;
        this.stillingstypeId = stillingstypeId;
    }

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

    public Lonn getLonnId() {
        return lonnId;
    }

    public void setLonnId(Lonn lonnId) {
        this.lonnId = lonnId;
    }

    public float getStillingsprosent() {
        return stillingsprosent;
    }

    public void setStillingsprosent(float stillingsprosent) {
        this.stillingsprosent = stillingsprosent;
    }

    public Stillingstype getStillingstypeId() {
        return stillingstypeId;
    }

    public void setStillingstypeId(Stillingstype stillingstypeId) {
        this.stillingstypeId = stillingstypeId;
    }

    /**
     * Returnerer en strengrepresentasjon av ansatteinformasjonen.
     * Formaterer viktig informasjon om ansatte for enkel visning.
     *
     * @return En streng som inneholder ansattes brukernavn, navn, kontaktinformasjon,
     * adresse, lønn, stillingsprosent, og stillingstype.
     */
    public String toString() {
        Postnummer post = adresseId.getPostnummer();
        return "[\"" +
                brukernavn + "\",\"" +
                etternavn + ", " + fornavn + "\",\"" +
                telefonnummer + "\",\"" +
                epost + "\",\"" +
                adresseId.getGatenavn() + " " +
                adresseId.getGatenummer() + "\",\"" +
                post.getPostnummer() + " " +
                post.getPoststed() + "\"," +
                String.format(Locale.US,"%.2f", stillingsprosent) + ",\"" +
                stillingstypeId.getStillingstype() + "\"," +
                lonnId.getTimelonn() + "]";
    }
}
