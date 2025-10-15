package esi.clubciber.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class EncryptionServiceTest {

    @Test
    void testEncryptDecrypt() throws Exception {
        // Generate a test key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey key = keyGen.generateKey();
        String stringKey = Base64.getEncoder().encodeToString(key.getEncoded());

        EncryptionService service = new EncryptionService(stringKey);

        String originalPassword = "testPassword123!@#";
        String encrypted = service.encrypt(originalPassword);
        String decrypted = service.decrypt(encrypted);

        assertEquals(originalPassword, decrypted);
    }

    @Test
    void testEncryptDecryptEmptyString() throws Exception {
        // Generate a test key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey key = keyGen.generateKey();
        String stringKey = Base64.getEncoder().encodeToString(key.getEncoded());

        EncryptionService service = new EncryptionService(stringKey);

        String originalPassword = "";
        String encrypted = service.encrypt(originalPassword);
        String decrypted = service.decrypt(encrypted);

        assertEquals(originalPassword, decrypted);
    }
}