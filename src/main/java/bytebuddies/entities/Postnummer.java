package bytebuddies.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representerer en postnummeroppføring i Workflux-systemet.
 * Inneholder informasjon om et postnummer og det tilhørende poststedet.
 */
@Entity
@Table(schema = "Workflux")
public class Postnummer {
    @Id
    private String postnummer;
    private String poststed;

    /**
     * Oppretter en ny postnummeroppføring med spesifisert postnummer og poststed.
     *
     * @param postnummer Postnummeret som identifiserer poststedet.
     * @param poststed   Navnet på poststedet tilknyttet postnummeret.
     */
    public Postnummer(String postnummer, String poststed) {
        this.postnummer = postnummer;
        this.poststed = poststed;
    }

    /**
     * Standard tom konstruktør.
     */
    public Postnummer() {
    }

    //Getter og setter-metoder
    public String getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(String postnummer) {
        this.postnummer = postnummer;
    }

    public String getPoststed() {
        return poststed;
    }

    public void setPoststed(String poststed) {
        this.poststed = poststed;
    }
}
