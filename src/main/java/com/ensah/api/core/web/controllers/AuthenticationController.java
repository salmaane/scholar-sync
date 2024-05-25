package com.ensah.api.core.web.controllers;

import com.ensah.api.core.dto.AuthenticationRequest;
import com.ensah.api.core.dto.AuthenticationResponse;
import com.ensah.api.core.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refresh(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        authService.refreshToken(request, response);
    }

}
