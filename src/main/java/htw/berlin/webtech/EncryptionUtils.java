package htw.berlin.webtech;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class EncryptionUtils {
/*
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String KEY = System.getenv("ENCRYPTION_KEY");

    private static Key getSecretKey() throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(KEY);
        return new SecretKeySpec(decodedKey, ALGORITHM);
    }

    public static String encrypt(String dataToEncrypt) throws Exception {
        Key secretKey = getSecretKey();
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(dataToEncrypt.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String dataToDecrypt) throws Exception {
        Key secretKey = getSecretKey();
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = Base64.getDecoder().decode(dataToDecrypt);
        return new String(cipher.doFinal(decryptedBytes));
    }
 */
}
