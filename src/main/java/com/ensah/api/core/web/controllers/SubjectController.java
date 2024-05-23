package com.ensah.api.core.web.controllers;

import com.ensah.api.core.models.Subject;
import com.ensah.api.core.services.SubjectService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController extends GenericController<Subject> {

    public SubjectController(SubjectService service) {
        super(service);
    }

}
