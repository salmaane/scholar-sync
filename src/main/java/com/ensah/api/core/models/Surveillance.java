package com.ensah.api.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Surveillance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Class aClass;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Exam exam;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "surveillances")
    private List<Professor> professors;

    @ManyToOne(fetch = FetchType.EAGER)
    private Professor coordinator;

    @ManyToOne(fetch = FetchType.EAGER)
    private Administrator absController;
}
