package com.ensah.api.core.web.controllers;

import com.ensah.api.core.dto.ExamDTO;
import com.ensah.api.core.models.Class;
import com.ensah.api.core.services.ClassService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/class")
public class ClassController extends GenericController<Class> {

    private final ClassService service;
    public ClassController(ClassService service) {
        super(service);
        this.service = service;
    }

    @PostMapping("/available")
    public List<Class> availableClasses(@RequestBody ExamDTO examDTO) {
        return service.availableClasses(examDTO);
    }

}