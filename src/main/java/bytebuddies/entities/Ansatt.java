package bytebuddies.entities;

import bytebuddies.embeddable.AnsattId;
import jakarta.persistence.*;

@Entity
@Table(schema = "Workflux")
public class Ansatt {
    @EmbeddedId
    private AnsattId ansattId;

    private String hash;
    private String salt;
    private String fornavn;
    private String etternavn;
    private String telefonnummer;
    private String epost;
    @ManyToOne
    @JoinColumn(name = "adresseId")
    private Adresse adresseId;
    private boolean isActive;
    private float stillingsprosent;
    private String stillingstype;


    public Ansatt(){}

}