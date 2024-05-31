package com.ensah.api.core.models;

import com.ensah.api.core.models.enums.GroupCreationCriteria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private GroupCreationCriteria creationCriteria;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private List<Professor> professors;

}
