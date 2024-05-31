package com.ensah.api.core.dto;

import com.ensah.api.core.models.Subject;
import com.ensah.api.core.models.enums.Level;
import com.ensah.api.core.models.enums.SubjectType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private Long id;
    private Level level;
    private SubjectType type;
    private String title;
    private Long professorId;
    private Long coordinatorId;

    public static SubjectDTO toDTO(Subject subject) {
        return SubjectDTO.builder()
                .id(subject.getId())
                .title(subject.getTitle())
                .level(subject.getLevel())
                .type(subject.getType())
                .build();
    }

    public static Subject toSubject(SubjectDTO subjectDTO) {
        return Subject.builder()
                .id(subjectDTO.getId())
                .title(subjectDTO.getTitle())
                .level(subjectDTO.getLevel())
                .type(subjectDTO.getType())
                .build();
    }
}
