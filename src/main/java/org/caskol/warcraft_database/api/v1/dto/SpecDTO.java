package org.caskol.warcraft_database.api.v1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class SpecDTO {
    private int id;
    private String name;
    private String description;
    private RoleDTO role;
    private WarcraftClassDTO warcraftClass;
    private IconDTO icon;
    private Set<StatDTO> stats;
}
