package bytebuddies.entities;

import jakarta.persistence.*;

/**
 * Representerer en type aktivitet eller oppgave som kan tildeles i en ansatts tidsplan innen Workflux-systemet.
 * Tidsplantypen kan være knyttet til spesifikke bedrifter, noe som muliggjør tilpasning av aktivitetstyper per bedrift.
 */
@Entity
@Table(schema = "Workflux")
public class Tidsplantype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tidsplantype_id")
    private Integer tidsplantypeId;

    private String type;

    @ManyToOne
    @JoinColumn(name = "bedrift_id")
    private Bedrift bedriftId;

    /**
     * Konstruerer en ny Tidsplantype med et spesifikt navn og en tilknyttet bedrift.
     *
     * @param type     Navnet på tidsplantypen som beskriver aktiviteten eller oppgaven.
     * @param bedriftId  Den bedriften tidsplantypen er tilknyttet.
     */
    public Tidsplantype(String type, Bedrift bedriftId) {
        this.type = type;
        this.bedriftId = bedriftId;
    }

    /**
     * Standard tom konstruktør.
     */
    public Tidsplantype() {
    }

    //Getter- og setter-metoder
    public Integer getTidsplantypeId() {
        return tidsplantypeId;
    }

    public void setTidsplantypeId(Integer tidsplantypeId) {
        this.tidsplantypeId = tidsplantypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Bedrift getBedriftId() {
        return bedriftId;
    }

    public void setBedriftId(Bedrift bedriftId) {
        this.bedriftId = bedriftId;
    }

    /**
     * Returnerer en strengrepresentasjon av tidsplantypen.
     * Formaterer informasjon om tidsplantypen for enkel visning eller logging.
     *
     * @return En streng som inneholder tidsplantypens ID og type.
     */
    public String toString() {
        return "[\"" +
                tidsplantypeId + "\",\"" +
                type + "\"]";
    }

}
