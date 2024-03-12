package bytebuddies.entities;

import jakarta.persistence.*;

@Entity
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adresseId;
    private String gatenavn;
    private String gatenummer;

    @ManyToOne
    @JoinColumn(name = "postnummer")
    private Postnummer postnummer;

    private boolean isActive;

    public Adresse(String gatenavn, String gatenummer, Postnummer postnummer, boolean isActive) {
        this.gatenavn = gatenavn;
        this.gatenummer = gatenummer;
        this.postnummer = postnummer;
        this.isActive = isActive;
    }
    public Adresse() {}

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
}
