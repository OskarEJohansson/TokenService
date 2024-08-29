package dev.oskarjohansson.projektarbetev2.controller;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;


@RestController
@RequestMapping("/public-key-controller")
public class PublicKeyController {

    private final Logger LOG = LoggerFactory.getLogger(PublicKeyController.class);
    private final RSAKey rsaKey;

    PublicKeyController(@Qualifier(value = "customRSAKey") RSAKey rsaKey){
        this.rsaKey = rsaKey;
    }

    @GetMapping("/v1/public-key")
    public ResponseEntity<?> publicKey() {
        try {return ResponseEntity.ok(rsaKey.toPublicJWK());}
        catch (Exception ex ){
            LOG.error("Error in /v1/public-key :"  + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error:" + ex.getMessage());
        }

    }
}
