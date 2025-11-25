import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;

public class ProtectSensitiveData {
    public static void main(String[] args) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException,
            InvalidParameterSpecException, InvalidAlgorithmParameterException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String data = "message";
        byte[] digest = md.digest(data.getBytes());
        String hash = (new BigInteger(1, digest)).toString(16);

        String text = "Value that requires encryption";
        SecretKey key = KeyGenerator.getInstance("AES").generateKey();
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] value = text.getBytes();
        byte[] encryptedValue = cipher.doFinal(value);
        GCMParameterSpec ps = cipher.getParameters().getParameterSpec(GCMParameterSpec.class);
        cipher.init(Cipher.DECRYPT_MODE, key, ps);
        byte[] decryptedValue = cipher.doFinal(encryptedValue);
    }
}
