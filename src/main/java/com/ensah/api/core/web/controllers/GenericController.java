package com.ensah.api.core.web.controllers;

import com.ensah.api.core.models.Department;
import com.ensah.api.core.services.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class GenericController<T> {

    private final GenericService<T> service;

    @GetMapping
    public List<T> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<T> findById(@PathVariable("id") Long id) {
        Optional<T> department = service.findById(id);
        return department.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<T> update(@PathVariable Long id, @RequestBody T newEntity) {
        T updatedEntity = service.update(id, newEntity);
        if(updatedEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEntity);
    }

}
