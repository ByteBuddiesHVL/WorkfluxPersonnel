package bytebuddies.embeddable;

import jakarta.persistence.Embeddable;

/**
 * Embeddable klasse som representerer passordet til en ansatt eller administrator.
 */
@Embeddable
public class Passord {
    private String hash;
    private String salt;

    /**
     * Konstruktør som oppretter en ny Passord-instans med angitt hash og salt.
     *
     * @param hash Den krypterte hashen av passordet.
     * @param salt Saltet som ble brukt under hashingen av passordet.
     */
    public Passord(String hash, String salt) {
        this.hash = hash;
        this.salt = salt;
    }

    /**
     * Tom konstruktør for Passord.
     */
    public Passord() {}

    //Getter- og setter-metoder
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
