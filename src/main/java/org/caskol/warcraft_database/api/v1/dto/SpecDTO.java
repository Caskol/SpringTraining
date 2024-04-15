package org.caskol.warcraft_database.api.v1.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SpecDTO {
    private Integer id;
    @NotBlank(message = "Это поле не может быть пустым")
    @Size(min = 4, message = "Это поле должно содержать как минимум 4 символа")
    private String name;
    private String description;
    @NotNull(message = "Это поле не может быть пустым")
    private RoleDTO role;
    @NotNull(message = "Это поле не может быть пустым")
    private WarcraftClassDTO warcraftClass;
    @NotNull(message = "Это поле не может быть пустым")
    private IconDTO icon;
    private List<StatDTO> stats;
}
