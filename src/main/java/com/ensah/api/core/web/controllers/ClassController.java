package com.ensah.api.core.web.controllers;

import com.ensah.api.core.models.Class;
import com.ensah.api.core.services.ClassService;
import com.ensah.api.core.services.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/class")
public class ClassController extends GenericController<Class> {

    public ClassController(ClassService service) {
        super(service);
    }

}