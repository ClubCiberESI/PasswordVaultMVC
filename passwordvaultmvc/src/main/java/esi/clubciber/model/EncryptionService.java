package esi.clubciber.model;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionService {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final int KEY_SIZE = 256;
    private static final int IV_SIZE = 16; // 128 bits for AES

    private SecretKey secretKey;
    private IvParameterSpec ivParameterSpec;

    public EncryptionService(String stringKey) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(KEY_SIZE);
            
            byte[] decodedKey = Base64.getDecoder().decode(stringKey);
            this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

            byte[] iv = new byte[IV_SIZE];
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(iv);
            this.ivParameterSpec = new IvParameterSpec(iv);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing encryption service", e);
        }
    }

    public String encrypt(String password) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes("UTF-8"));
            byte[] encryptedWithIv = new byte[IV_SIZE + encryptedBytes.length];
            System.arraycopy(ivParameterSpec.getIV(), 0, encryptedWithIv, 0, IV_SIZE);
            System.arraycopy(encryptedBytes, 0, encryptedWithIv, IV_SIZE, encryptedBytes.length);
            return Base64.getEncoder().encodeToString(encryptedWithIv);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting password", e);
        }
    }

    public String decrypt(String encryptedPassword) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
            byte[] iv = new byte[IV_SIZE];
            byte[] encryptedBytes = new byte[decodedBytes.length - IV_SIZE];
            System.arraycopy(decodedBytes, 0, iv, 0, IV_SIZE);
            System.arraycopy(decodedBytes, IV_SIZE, encryptedBytes, 0, encryptedBytes.length);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting password", e);
        }
    }
}