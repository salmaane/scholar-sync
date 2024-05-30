package com.ensah.api.core.web.controllers;

import com.ensah.api.core.dto.UserDTO;
import com.ensah.api.core.models.ProfGroup;
import com.ensah.api.core.models.Professor;
import com.ensah.api.core.services.GenericService;
import com.ensah.api.core.services.ProfGroupService;
import com.ensah.api.core.services.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/group")
public class GroupController extends GenericController<ProfGroup> {
    private final ProfessorService professorService;
    public GroupController(ProfGroupService service, ProfessorService professorService) {
        super(service);
        this.professorService = professorService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/prof")
    public ResponseEntity<List<UserDTO>> getAllProfessors() {
        List<UserDTO> professors = professorService.getAllProfessors();
        return ResponseEntity.ok(professors);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/prof/department/{departmentId}")
    public ResponseEntity<List<UserDTO>> getProfessorsByDepartment(@PathVariable Long departmentId) {
        List<UserDTO> professors = professorService.getProfessorsByDepartment(departmentId);
        return ResponseEntity.ok(professors);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/prof/sector/{sectorId}")
    public ResponseEntity<List<UserDTO>> getProfessorsBySector(@PathVariable Long sectorId) {
        List<UserDTO> professors = professorService.getProfessorsBySector(sectorId);
        return ResponseEntity.ok(professors);
    }

}
