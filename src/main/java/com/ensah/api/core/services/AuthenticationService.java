package com.ensah.api.core.services;

import com.ensah.api.core.dao.TokenDAO;
import com.ensah.api.core.dao.UserDAO;
import com.ensah.api.core.models.Administrator;
import com.ensah.api.core.models.Professor;
import com.ensah.api.core.models.Token;
import com.ensah.api.core.models.User;
import com.ensah.api.core.dto.AuthenticationRequest;
import com.ensah.api.core.dto.AuthenticationRespnse;
import com.ensah.api.core.dto.RegisterRequest;
import com.ensah.api.core.models.enums.Role;
import com.ensah.api.core.models.enums.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserDAO userDAO;
    private final TokenDAO tokenDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationRespnse register(RegisterRequest request) {

        User user;
        User savedUser;
        if(request.getRole() == Role.ADMIN) {
            user = Administrator.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();
            savedUser = userDAO.save(user);
        } else if (request.getRole() == Role.PROF) {
            user = Professor.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();
            savedUser = userDAO.save(user);
        } else {
            throw new IllegalArgumentException("Invalid Role: " + request.getRole());
        }

        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationRespnse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationRespnse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userDAO.findUserByEmail(request.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationRespnse.builder()
                .token(jwtToken)
                .build();
    }

    private void revokeAllUserTokens(User user) {
        var userValidTokens = tokenDAO.findAllValidTokensByUser(user.getId());
        if(userValidTokens.isEmpty()) {
            return;
        }
        userValidTokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        tokenDAO.saveAll(userValidTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .type(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenDAO.save(token);
    }
}
