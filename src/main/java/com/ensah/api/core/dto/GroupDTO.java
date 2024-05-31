package com.ensah.api.core.dto;

import com.ensah.api.core.models.ProfGroup;
import com.ensah.api.core.models.enums.GroupCreationCriteria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    private Long id;
    private String name;
    private GroupCreationCriteria creationCriteria;
    private List<Long> users = new ArrayList<>();

    public static GroupDTO toDTO(ProfGroup group) {
        return GroupDTO.builder()
                .id(group.getId())
                .name(group.getName())
                .creationCriteria(group.getCreationCriteria())
                .build();
    }

    public static ProfGroup toProfGroup(GroupDTO groupDTO) {
        return  ProfGroup.builder()
                .id(groupDTO.id)
                .name(groupDTO.getName())
                .creationCriteria(groupDTO.getCreationCriteria())
                .build();
    }
}
