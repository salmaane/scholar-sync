package com.ensah.api.core.services;

import com.ensah.api.core.dao.UserDAO;
import com.ensah.api.core.models.Administrator;
import com.ensah.api.core.models.Professor;
import com.ensah.api.core.models.User;
import com.ensah.api.core.dto.AuthenticationRequest;
import com.ensah.api.core.dto.AuthenticationRespnse;
import com.ensah.api.core.dto.RegisterRequest;
import com.ensah.api.core.models.enums.Role;
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

        User user;
        if(request.getRole() == Role.ADMIN) {
            user = Administrator.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .phoneNumber(request.getPhoneNumber())
                    .build();
            userDAO.save(user);
        } else if (request.getRole() == Role.PROF) {
            user = Professor.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .cin(request.getCin())
                    .build();
            userDAO.save(user);
        } else {
            throw new IllegalArgumentException("Invalid Role: " + request.getRole());
        }

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
