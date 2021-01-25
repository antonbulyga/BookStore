package com.senla.model.сontrollers;

import com.senla.model.dto.AuthenticationRequestDto;
import com.senla.model.entity.User;
import com.senla.model.security.JwtTokenProvider;
import com.senla.model.service.api.TokenService;
import com.senla.model.service.api.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth/")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final TokenService tokenService;
    private static final Logger logger = Logger.getLogger(AuthenticationController.class);

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            User user = userService.findByUsername(username);
            if (user.getUserName() == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }
            else {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
                String token = jwtTokenProvider.createToken(username, user.getRoles());
                Map<Object, Object> response = new HashMap<>();
                response.put("username", username);
                response.put("token", token);

                return ResponseEntity.ok(response);
            }

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping("exit")
    public ResponseEntity logout(@RequestHeader(name = "Authorization") String authorization) {
        if (authorization != null && authorization.startsWith("Bearer_")) {
            String token = authorization.substring(7, authorization.length());
            tokenService.addToken(token);
            logger.info("You are logged out");
        }
        return ResponseEntity.ok()
                .body("You are logged out");
    }
}
