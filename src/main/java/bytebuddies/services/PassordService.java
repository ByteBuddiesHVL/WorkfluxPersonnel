package bytebuddies.services;

import jakarta.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Tjenesteklasse for å hashe og salte passord,
 * i tillegg til å sjekke om passord er korrekt eller ikke.
 */
@Service
public class PassordService {

    /**
     * Genererer et tilfeldig salt ved hjelp av SHA1PRNG SecureRandom-algoritmen.
     *
     * @return En tekststreng som representerer det tilfeldige saltet i heksadesimalformat.
     */
    public String genererTilfeldigSalt() {
        SecureRandom sr;
        byte[] salt = new byte[16];
        try {
            sr = SecureRandom.getInstance("SHA1PRNG");
            sr.nextBytes(salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return DatatypeConverter.printHexBinary(salt);
    }

    /**
     * Hasher et passord med et gitt salt ved hjelp av PBKDF2WithHmacSHA1 algoritmen.
     *
     * @param passord Passordet som skal hashes.
     * @param salt    Saltet som skal brukes i hashing-prosessen.
     * @return En tekststreng som representerer det hashede passordet i heksadesimalformat.
     */
    public String hashMedSalt(String passord, String salt) {

        if (passord == null || salt == null) {
            throw new IllegalArgumentException();
        }

        char[] passchar = passord.toCharArray();
        byte[] saltbytes = DatatypeConverter.parseHexBinary(salt);

        byte[] keyhash = null;
        try {
            PBEKeySpec pks = new PBEKeySpec(passchar, saltbytes, 1000, 256);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            keyhash = skf.generateSecret(pks).getEncoded();

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return DatatypeConverter.printHexBinary(keyhash);
    }

    /**
     * Sjekker om et gitt passord matcher hashen som er laget med det gitt saltet.
     *
     * @param passord Passordet som skal sjekkes.
     * @param salt    Saltet som ble brukt til å lage hashen.
     * @param hash    Hashen som passordet skal matches mot.
     * @return True hvis passordet matcher hashen, ellers false.
     */
    public boolean erKorrektPassord(String passord, String salt, String hash) {

        if (passord == null || salt == null || hash == null) {
            throw new IllegalArgumentException();
        }

        return hash.equals(hashMedSalt(passord, salt));
    }
}
