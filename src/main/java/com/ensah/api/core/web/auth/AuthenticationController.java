package com.ensah.api.core.web.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @PostMapping("/register")
    public ResponseEntity<AuthenticationRespnse> register(
            @RequestBody RegisterRequest request
    ) {
        return null;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationRespnse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return null;
    }

}
