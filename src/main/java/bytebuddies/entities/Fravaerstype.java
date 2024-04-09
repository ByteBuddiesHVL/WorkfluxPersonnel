package bytebuddies.entities;

import jakarta.persistence.*;

/**
 * Representerer en kategori eller type av fravær i Workflux-systemet.
 * Hver {@code Fravaerstype} er definert ved en unik ID og et navn som beskriver typen fravær.
 */
@Entity
@Table(schema = "Workflux")
public class Fravaerstype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Integer typeId;
    private String navn;

    /**
     * Oppretter en ny instans av {@code Fravaerstype} med et spesifisert navn.
     * Denne konstruktøren brukes for å lage nye fraværstyper.
     *
     * @param navn Navnet på fraværstypen, som beskriver hva slags fravær det refererer til.
     */
    public Fravaerstype(String navn) {
        this.navn = navn;
    }

    /**
     * Standard tom konstruktør nødvendig for JPA-entiteter.
     */
    public Fravaerstype() {
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
}
