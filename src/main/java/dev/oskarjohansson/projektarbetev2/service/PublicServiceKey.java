package dev.oskarjohansson.projektarbetev2.service;

import org.springframework.dao.DataAccessException;

import java.security.interfaces.RSAPublicKey;

public interface PublicServiceKey {

    public void setRsaPublicKey(RSAPublicKey PublicKey) throws DataAccessException;
    public RSAPublicKey getRsaPublicKey() throws DataAccessException;
}
