package com.senla.model.service;

import com.senla.model.repository.api.TokenRepository;
import com.senla.model.service.api.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Boolean findByToken(String token) {
        Boolean bool = tokenRepository.findByToken(token);
        return bool;
    }

    @Override
    public void addToken(String tokenName) {
        tokenRepository.addToken(tokenName);
    }
}
