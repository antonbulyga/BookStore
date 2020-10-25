package com.senla.model.repository.api;

public interface TokenRepository {
    Boolean findByToken(String token);
    void addToken(String tokenName);
}
