package com.ensah.api.core.web.controllers;

import com.ensah.api.core.models.Class;
import com.ensah.api.core.services.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/class")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService service;

    @GetMapping
    public List<Class> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Class> findById(@PathVariable("id") Long id) {
        Optional<Class> aClass = service.findById(id);
        return aClass.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Class> create(@RequestBody Class aClass) {
        return new ResponseEntity<>(service.save(aClass), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Class> update(@PathVariable Long id, @RequestBody Class newClass) {
        Class updatedClass = service.update(id, newClass);
        if(updatedClass == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedClass);
    }

}