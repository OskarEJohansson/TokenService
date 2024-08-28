package dev.oskarjohansson.projektarbetev2.service.Impl;


import dev.oskarjohansson.projektarbetev2.service.PublicServiceKey;
import jakarta.annotation.PostConstruct;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;

@Service
public class PublicKeyServiceImpl implements PublicServiceKey {

    private RSAPublicKey rsaPublicKey;

    public void setRsaPublicKey(RSAPublicKey PublicKey) throws DataAccessException {
        System.out.println(PublicKey);
        this.rsaPublicKey = PublicKey;
    }

    public RSAPublicKey getRsaPublicKey() throws DataAccessException {
        return this.rsaPublicKey;
    }

}
