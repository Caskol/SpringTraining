package org.caskol.warcraft_database.api.v1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDTO {
    private int id;
    private String name;
    private IconDTO icon;
}
