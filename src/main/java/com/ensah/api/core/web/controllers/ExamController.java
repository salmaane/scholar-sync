package com.ensah.api.core.web.controllers;

import com.ensah.api.core.dto.ExamDTO;
import com.ensah.api.core.services.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService service;

    @PostMapping
    public ResponseEntity<ExamDTO> create(@RequestBody ExamDTO examDTO){
        ExamDTO exam = service.create(examDTO);
        if(exam == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(exam);
        }
    }

    @PostMapping("/byYear")
    public List<ExamDTO> findAll(@RequestBody ExamDTO examDTO) {
        return service.findAll(examDTO.getAcademicYear());
    }

    @GetMapping("years")
    public String[] getAcademicYears() {
        return service.getAcademicYears();
    }
}
