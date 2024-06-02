package com.ensah.api.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends User{

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Surveillance> surveillances = new ArrayList<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "coordinator")
    private List<Surveillance> managed_surveillances = new ArrayList<>();

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
