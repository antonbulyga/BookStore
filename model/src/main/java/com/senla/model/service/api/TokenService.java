package com.senla.model.service.api;

public interface TokenService {
    Boolean findByToken(String token);
    void addToken(String tokenName);
}
