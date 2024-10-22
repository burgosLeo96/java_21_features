package examples.java21.keyEncapsulation;

import javax.crypto.DecapsulateException;
import javax.crypto.KEM;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;

// Key encapsulation is a modern cryptographic technique that secures
// symmetric keys using asymmetric or public key cryptography.
// A key encapsulation mechanism (KEM) uses properties of the
// public key to derive a related symmetric key, which requires no padding.
public class KeyEncapsulationExample {

    public static void keyEncapsulationExample() throws NoSuchAlgorithmException, InvalidKeyException, DecapsulateException, InvalidAlgorithmParameterException {

        // Generate a new key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        keyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Sender side
        // Generate a new encapsulated key
        KEM kem = KEM.getInstance("DHKEM");
        var encapsulator = kem.newEncapsulator(keyPair.getPublic());
        KEM.Encapsulated encapsulated = encapsulator.encapsulate(); // This method generated a shared key
        var k1 = encapsulated.key();

        // Receiver side
        // Decapsulate the key
        KEM kem2 = KEM.getInstance("DHKEM");
        var decapsulator = kem2.newDecapsulator(keyPair.getPrivate());
        var k2 = decapsulator.decapsulate(encapsulated.encapsulation());

        // Print the secret key
        System.out.println("Secret Key: " + k2);
        assert Arrays.equals(k1.getEncoded(), k2.getEncoded());
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, DecapsulateException, InvalidAlgorithmParameterException {
        keyEncapsulationExample();
    }
}
