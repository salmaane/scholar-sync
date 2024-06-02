package com.ensah.api.core.web.controllers;

import com.ensah.api.core.dto.GroupDTO;
import com.ensah.api.core.dto.UserDTO;
import com.ensah.api.core.models.ProfGroup;
import com.ensah.api.core.services.ProfGroupService;
import com.ensah.api.core.services.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/group")
@PreAuthorize("hasAuthority('ADMIN')")
public class GroupController extends GenericController<ProfGroup> {
    private final ProfessorService professorService;
    private final ProfGroupService profGroupService;
    public GroupController(ProfGroupService service, ProfessorService professorService) {
        super(service);
        this.professorService = professorService;
        this.profGroupService = service;
    }

    @GetMapping("/prof")
    public ResponseEntity<List<UserDTO>> getAllProfessors() {
        List<UserDTO> professors = professorService.getAll();
        return ResponseEntity.ok(professors);
    }
    @GetMapping("/prof/department/{departmentId}")
    public ResponseEntity<List<UserDTO>> getProfessorsByDepartment(@PathVariable Long departmentId) {
        List<UserDTO> professors = professorService.getByDepartment(departmentId);
        return ResponseEntity.ok(professors);
    }

    @GetMapping("/prof/sector/{sectorId}")
    public ResponseEntity<List<UserDTO>> getProfessorsBySector(@PathVariable Long sectorId) {
        List<UserDTO> professors = professorService.getBySector(sectorId);
        return ResponseEntity.ok(professors);
    }

    @PostMapping("create")
    public ResponseEntity<ProfGroup> create(@RequestBody GroupDTO group) {
        return new ResponseEntity<>(profGroupService.save(group), HttpStatus.CREATED);
    }
}
