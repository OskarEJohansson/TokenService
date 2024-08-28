package dev.oskarjohansson.projektarbetev2.controller;

import dev.oskarjohansson.projektarbetev2.service.Impl.PublicKeyServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.interfaces.RSAPublicKey;

@RestController
@RequestMapping("/public-key-controller")
public class PublicKeyController {

    private final PublicKeyServiceImpl publicKeyService;

    public PublicKeyController(PublicKeyServiceImpl publicKeyService) {
        this.publicKeyService = publicKeyService;
    }

    @GetMapping("/v1/public-key")
    public ResponseEntity<?> publicKey() {
            return ResponseEntity.ok("Public key: " + publicKeyService.getRsaPublicKey().getPublicExponent());
    }
}
