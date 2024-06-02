package com.ensah.api.core.web.controllers;

import com.ensah.api.core.dto.ExamDTO;
import com.ensah.api.core.services.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
