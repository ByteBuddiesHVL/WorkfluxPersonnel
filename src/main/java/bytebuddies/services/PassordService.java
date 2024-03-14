package bytebuddies.services;

import jakarta.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

@Service
public class PassordService {

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

    public boolean erKorrektPassord(String passord, String salt, String hash) {

        if (passord == null || salt == null || hash == null) {
            throw new IllegalArgumentException();
        }

        return hash.equals(hashMedSalt(passord, salt));
    }
}
