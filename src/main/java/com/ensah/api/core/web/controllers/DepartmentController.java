package com.ensah.api.core.web.controllers;

import com.ensah.api.core.models.Department;
import com.ensah.api.core.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController extends GenericController<Department>{

    public DepartmentController(DepartmentService service) {
        super(service);
    }

}
