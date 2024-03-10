package bytebuddies.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class Tidsplan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int timeplanId;

    private LocalDateTime starttid;
    private LocalDateTime sluttid;

    @OneToMany(mappedBy="ansatt")
    private List<Ansatt> ansattId;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bedriftId")
    private Bedrift bedriftId;

    public Tidsplan(){}
    public Tidsplan(int timeplanId, LocalDateTime starttid, LocalDateTime sluttid, List<Ansatt> ansattId, Bedrift bedriftId) {
        this.timeplanId = timeplanId;
        this.starttid = starttid;
        this.sluttid = sluttid;
        this.ansattId = ansattId;
        this.bedriftId = bedriftId;
    }

    public int getTimeplanId() {
        return timeplanId;
    }

    public void setTimeplanId(int timeplan_id) {
        this.timeplanId = timeplan_id;
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

    public List<Ansatt> getAnsattId() {
        return ansattId;
    }

    public void setAnsattId(List<Ansatt> ansatt_id) {
        this.ansattId = ansatt_id;
    }

    public Bedrift getBedriftId() {
        return bedriftId;
    }

    public void setBedriftId(Bedrift bedrift_id) {
        this.bedriftId = bedrift_id;
    }
}
