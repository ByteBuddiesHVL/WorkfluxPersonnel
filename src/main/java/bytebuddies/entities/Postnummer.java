package bytebuddies.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "Workflux")
public class Postnummer {
    @Id
    private String postnummer;
    private String poststed;

    public Postnummer(String postnummer, String poststed) {
        this.postnummer = postnummer;
        this.poststed = poststed;
    }

    public Postnummer() {
    }

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
