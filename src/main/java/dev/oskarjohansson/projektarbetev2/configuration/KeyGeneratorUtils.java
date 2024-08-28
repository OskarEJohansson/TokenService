package dev.oskarjohansson.projektarbetev2.configuration;

import org.springframework.stereotype.Component;

import java.security.*;

@Component
final class KeyGeneratorUtils {

    private KeyGeneratorUtils() {
    }

    static KeyPair generateRsaKey() {
        KeyPair keyPair;

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();;
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }

        return keyPair;
    }
}
