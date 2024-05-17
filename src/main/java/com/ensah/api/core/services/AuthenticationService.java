package com.ensah.api.core.services;

import com.ensah.api.core.dao.UserDAO;
import com.ensah.api.core.models.User;
import com.ensah.api.core.web.auth.AuthenticationRequest;
import com.ensah.api.core.web.auth.AuthenticationRespnse;
import com.ensah.api.core.web.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationRespnse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userDAO.save(user);
        var jwtToken = jwtService.generateToken(user);
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
        return AuthenticationRespnse.builder()
                .token(jwtToken)
                .build();
    }
}
