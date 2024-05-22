package com.ensah.api.core.web.controllers;

import com.ensah.api.core.models.ProfGroup;
import com.ensah.api.core.services.ProfGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/group")
@RequiredArgsConstructor
public class GroupController {

    private final ProfGroupService service;

    @GetMapping
    public List<ProfGroup> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfGroup> findById(@PathVariable("id") Long id) {
        Optional<ProfGroup> group = service.findById(id);
        return group.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ProfGroup> create(@RequestBody ProfGroup group) {
        return new ResponseEntity<>(service.save(group), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProfGroup> update(@PathVariable Long id, @RequestBody ProfGroup newGroup) {
        ProfGroup updatedGroup = service.update(id, newGroup);
        if(updatedGroup == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedGroup);
    }

}
