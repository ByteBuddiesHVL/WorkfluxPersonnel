package bytebuddies.entities;

import jakarta.persistence.*;

@Entity
@Table(schema="Workflux")
public class Bedrift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bedriftId;

    private String navn;

    public Bedrift(){}
    public Bedrift(int bedriftId, String navn) {
        this.bedriftId = bedriftId;
        this.navn = navn;
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
}

