package com.ensah.api.core.web.controllers;

import com.ensah.api.core.models.Sector;
import com.ensah.api.core.services.GenericService;
import com.ensah.api.core.services.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sector")
public class SectorController extends GenericController<Sector> {

    public SectorController(SectorService service) {
        super(service);
    }

}
