package org.caskol.warcraft_database.api.v1.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SpecDTO {
    private int id;
    private String name;
    private String description;
    private RoleDTO role;
    private WarcraftClassDTO warcraftClass;
    private IconDTO icon;
    private List<StatDTO> stats;
}
