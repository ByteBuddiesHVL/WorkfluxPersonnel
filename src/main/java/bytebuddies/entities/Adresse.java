package bytebuddies.entities;

import jakarta.persistence.*;

/**
 * Representerer en adresse i Workflux-systemet.
 * Inkluderer informasjon om gatenavn, gatenummer, postnummer og en aktivitetsstatus.
 */
@Entity
@Table(schema = "Workflux")
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adresse_id")
    private Integer adresseId;
    private String gatenavn;
    private String gatenummer;

    @ManyToOne
    @JoinColumn(name = "postnummer")
    private Postnummer postnummer;
    @Column(name = "is_active")
    private boolean isActive;

    /**
     * Konstruerer en Adresse med gitt gatenavn, gatenummer, postnummer og aktivitetsstatus.
     *
     * @param gatenavn    Gatenavnet til adressen.
     * @param gatenummer  Gatenummeret til adressen.
     * @param postnummer  Postnummeret til adressen.
     * @param isActive    Angir om adressen er aktiv.
     */
    public Adresse(String gatenavn, String gatenummer, Postnummer postnummer, boolean isActive) {
        this.gatenavn = gatenavn;
        this.gatenummer = gatenummer;
        this.postnummer = postnummer;
        this.isActive = isActive;
    }
    /**
     * Standard tom konstruktør.
     */
    public Adresse() {}

    //Getter- og setter-metoder
    public Integer getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Integer adresseId) {
        this.adresseId = adresseId;
    }

    public String getGatenavn() {
        return gatenavn;
    }

    public void setGatenavn(String gatenavn) {
        this.gatenavn = gatenavn;
    }

    public String getGatenummer() {
        return gatenummer;
    }

    public void setGatenummer(String gatenummer) {
        this.gatenummer = gatenummer;
    }

    public Postnummer getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(Postnummer postnummer) {
        this.postnummer = postnummer;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Returnerer en strengrepresentasjon av adressen.
     *
     * @return En streng som representerer adressen.
     */
    @Override
    public String toString() {
        return gatenavn + " " + gatenummer + ", " + postnummer.getPostnummer() + " " + postnummer.getPoststed();
    }
}
