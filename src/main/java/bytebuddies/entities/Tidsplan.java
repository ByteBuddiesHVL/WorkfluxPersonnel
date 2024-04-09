package bytebuddies.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Locale;

/**
 * Representerer en tidsplan for en ansatt i Workflux-systemet.
 * Inkluderer informasjon om start- og sluttidspunkt, typen aktivitet, og om tidsplanen er beregnet automatisk.
 */
@Entity
@Table(schema = "Workflux")
public class Tidsplan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tidsplan_id")
    private int tidsplanId;

    @ManyToOne
    @JoinColumn(name = "ansatt_id")
    private Ansatt ansattId;

    private LocalDateTime starttid;
    private LocalDateTime sluttid;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Tidsplantype typeId;

    /**
     * Indikerer om tidsplanen er automatisk beregnet.
     */
    @Column(name = "is_calced")
    private Boolean isCalced;

    /**
     * Oppretter en ny {@code Tidsplan} med tilknyttet ansatt, start- og sluttid, tidsplantype, og om den er beregnet.
     *
     * @param ansattId  Den ansatte tidsplanen tilhører.
     * @param starttid  Starttidspunktet for tidsplanen.
     * @param sluttid   Sluttidspunktet for tidsplanen.
     * @param typeId    Typen av aktivitet i tidsplanen.
     * @param isCalced  Om tidsplanen er automatisk beregnet.
     */
    public Tidsplan(Ansatt ansattId, LocalDateTime starttid, LocalDateTime sluttid, Tidsplantype typeId, Boolean isCalced) {
        this.ansattId = ansattId;
        this.starttid = starttid;
        this.sluttid = sluttid;
        this.typeId = typeId;
        this.isCalced = isCalced;
    }

    /**
     * Standard tom konstruktør.
     */
    public Tidsplan() {}

    //Getter- og setter-metoder

    public int getTidsplanId() {
        return tidsplanId;
    }

    public void setTidsplanId(int tidsplanId) {
        this.tidsplanId = tidsplanId;
    }

    public Ansatt getAnsattId() {
        return ansattId;
    }

    public void setAnsattId(Ansatt ansattId) {
        this.ansattId = ansattId;
    }

    public LocalDateTime getStarttid() {
        return starttid;
    }

    public void setStarttid(LocalDateTime starttid) {
        this.starttid = starttid;
    }

    public LocalDateTime getSluttid() {
        return sluttid;
    }

    public void setSluttid(LocalDateTime sluttid) {
        this.sluttid = sluttid;
    }

    public Tidsplantype getTypeId() {
        return typeId;
    }

    public void setTypeId(Tidsplantype type_id) {
        this.typeId = type_id;
    }

    public Boolean getCalced() {
        return isCalced;
    }

    public void setCalced(Boolean calced) {
        isCalced = calced;
    }

    /**
     * Returnerer en strengrepresentasjon av tidsplanen.
     * Formaterer viktig informasjon om tidsplanen for enkel visning.
     *
     * @return En streng som inneholder detaljer om tidsplanen.
     */
    public String toString() {
        return "[" +
                tidsplanId + ",\"" +
                ansattId.getBrukernavn() + "\",\"" +
                ansattId.getFornavn() + " " + ansattId.getEtternavn() + "\",\"" +
                starttid + "\",\"" +
                sluttid + "\"," +
                typeId.getTidsplantypeId() + ",\"" +
                typeId.getType() + "\"]";
    }
}
