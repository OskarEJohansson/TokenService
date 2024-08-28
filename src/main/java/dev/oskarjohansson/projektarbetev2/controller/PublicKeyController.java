package dev.oskarjohansson.projektarbetev2.controller;

import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/public-key-controller")
public class PublicKeyController {

    private final RSAKey rsaKey;

    PublicKeyController(RSAKey rsaKey){
        this.rsaKey = rsaKey;
    }

    @GetMapping("/v1/public-key")
    public ResponseEntity<?> publicKey() {
            return ResponseEntity.ok(rsaKey.toPublicJWK());
    }
}
