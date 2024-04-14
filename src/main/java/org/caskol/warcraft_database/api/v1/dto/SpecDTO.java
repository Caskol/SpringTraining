package org.caskol.warcraft_database.api.v1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SpecDTO {
    private int id;
    @NotBlank(message = "Это поле не может быть пустым")
    @Size(min = 4, message = "Это поле должно содержать как минимум 4 символа")
    private String name;
    private String description;
    @NotBlank(message = "Это поле не может быть пустым")
    private RoleDTO role;
    @NotBlank(message = "Это поле не может быть пустым")
    private WarcraftClassDTO warcraftClass;
    @NotBlank(message = "Это поле не может быть пустым")
    private IconDTO icon;
    private List<StatDTO> stats;
}
