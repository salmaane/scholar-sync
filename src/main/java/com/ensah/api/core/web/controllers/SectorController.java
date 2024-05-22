package com.ensah.api.core.web.controllers;

import com.ensah.api.core.models.Sector;
import com.ensah.api.core.services.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sector")
@RequiredArgsConstructor
public class SectorController {

    private final SectorService service;

    @GetMapping
    public List<Sector> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Sector> findById(@PathVariable("id") Long id) {
        Optional<Sector> sector = service.findById(id);
        return sector.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Sector> create(@RequestBody Sector sector) {
        return new ResponseEntity<>(service.save(sector), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Sector> update(@PathVariable Long id, @RequestBody Sector newSector) {
        Sector updatedSector = service.update(id, newSector);
        if(updatedSector == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedSector);
    }


}
