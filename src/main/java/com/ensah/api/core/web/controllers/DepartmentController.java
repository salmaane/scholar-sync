package com.ensah.api.core.web.controllers;

import com.ensah.api.core.models.Department;
import com.ensah.api.core.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService service;

    @GetMapping
    public List<Department> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Department> findById(@PathVariable("id") Long id) {
        Optional<Department> department = service.findById(id);
        return department.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Department> create(@RequestBody Department department) {
        return new ResponseEntity<>(service.save(department), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Department> update(@PathVariable Long id, @RequestBody Department newDepartment) {
        Department updatedDepartment = service.update(id, newDepartment);
        if(updatedDepartment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedDepartment);
    }


}
