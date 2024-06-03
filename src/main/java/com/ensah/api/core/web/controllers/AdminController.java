package com.ensah.api.core.web.controllers;

import com.ensah.api.core.dto.AuthenticationResponse;
import com.ensah.api.core.dto.RegisterRequest;
import com.ensah.api.core.services.AdminService;
import com.ensah.api.core.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AuthenticationService authService;
    private final AdminService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("count")
    public ResponseEntity<Long> rowsNumber(){
        return ResponseEntity.ok(service.rowsNumber());
    }

}
