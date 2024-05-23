package com.ensah.api.core.web.controllers;

import com.ensah.api.core.models.ProfGroup;
import com.ensah.api.core.services.GenericService;
import com.ensah.api.core.services.ProfGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/group")
public class GroupController extends GenericController<ProfGroup> {

    public GroupController(ProfGroupService service) {
        super(service);
    }

}
