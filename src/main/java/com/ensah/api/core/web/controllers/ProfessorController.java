package com.ensah.api.core.web.controllers;

import com.ensah.api.core.services.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/professor")
@RequiredArgsConstructor
public class ProfessorController {
    final private ProfessorService service;

    @GetMapping("count")
    public ResponseEntity<Long> rowsNumber(){
        return ResponseEntity.ok(service.rowsNumber());
    }
}
