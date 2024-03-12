package bytebuddies.entities;

import jakarta.persistence.*;

@Entity
@Table(schema="Workflux")
public class Bedrift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bedriftId;
    private String navn;
    private String forkortelse;

    public Bedrift(){}

    public Bedrift(String navn, String forkortelse) {
        this.navn = navn;
        this.forkortelse = forkortelse;
    }

    public int getBedriftId() {
        return bedriftId;
    }

    public void setBedriftId(int bedriftId) {
        this.bedriftId = bedriftId;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getForkortelse() {
        return forkortelse;
    }

    public void setForkortelse(String forkortelse) {
        this.forkortelse = forkortelse;
    }
}

