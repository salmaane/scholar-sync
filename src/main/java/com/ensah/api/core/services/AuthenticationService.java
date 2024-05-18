package com.ensah.api.core.services;

import com.ensah.api.core.dao.RoleDAO;
import com.ensah.api.core.dao.UserDAO;
import com.ensah.api.core.models.Role;
import com.ensah.api.core.models.User;
import com.ensah.api.core.dto.AuthenticationRequest;
import com.ensah.api.core.dto.AuthenticationRespnse;
import com.ensah.api.core.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationRespnse register(RegisterRequest request) {
        Role userRole = roleDAO.findByName("ROLE_PROF");

        if(userRole == null) {
            throw new RuntimeException("Role not found");
        }

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of(userRole))
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
