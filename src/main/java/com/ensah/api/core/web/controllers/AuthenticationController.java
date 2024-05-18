package com.ensah.api.core.web.controllers;

import com.ensah.api.core.dto.AuthenticationRequest;
import com.ensah.api.core.dto.AuthenticationRespnse;
import com.ensah.api.core.dto.RegisterRequest;
import com.ensah.api.core.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationRespnse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
