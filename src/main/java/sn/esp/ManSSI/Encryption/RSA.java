package sn.esp.ManSSI.Encryption;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import javax.crypto.Cipher;

public class RSA {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    public RSA() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // Taille de la clé
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
    }

    public byte[] encrypt(byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    public byte[] decrypt(byte[] encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(encryptedData);
    }

    public PublicKey getPublicKey() { return publicKey; }

    public PrivateKey getPrivateKey() { return privateKey; }

//    public static void main(String[] args) throws Exception {
//        // Exemple d'utilisation
//        RSA rsa = new RSA();
//        String message = "Hello, world!";
//        byte[] encryptedData = rsa.encrypt(message.getBytes());
//        byte[] decryptedData = rsa.decrypt(encryptedData);
//        System.out.println("Original message: " + message);
//        System.out.println("Decrypted message: " + new String(decryptedData));
//    }
}
