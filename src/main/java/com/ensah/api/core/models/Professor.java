package com.ensah.api.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends User{

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Surveillance> surveillances;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "coordinator")
    private Set<Surveillance> managed_surveillances;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Sector sector;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private ProfGroup group;

}
