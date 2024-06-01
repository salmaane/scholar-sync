package com.ensah.api.core.web.controllers;

import com.ensah.api.core.dto.InformationDTO;
import com.ensah.api.core.models.Information;
import com.ensah.api.core.services.InformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/information")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class InformationController {

    private final InformationService service;

    @GetMapping
    public InformationDTO getInformation() {
        return service.getInformation();
    }

}
