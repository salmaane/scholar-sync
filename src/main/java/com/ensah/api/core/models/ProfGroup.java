package com.ensah.api.core.models;

import com.ensah.api.core.models.enums.GroupCreationCriteria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
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
    private Set<Professor> professors;

}
