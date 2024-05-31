package com.ensah.api.core.web.controllers;

import com.ensah.api.core.dto.SubjectDTO;
import com.ensah.api.core.models.Subject;
import com.ensah.api.core.services.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController extends GenericController<Subject> {

    private final SubjectService service;
    public SubjectController(SubjectService service) {
        super(service);
        this.service = service;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("create")
    public ResponseEntity<Subject> create(@RequestBody SubjectDTO subject) {
        return new ResponseEntity<>(service.save(subject), HttpStatus.CREATED);
    }

}
