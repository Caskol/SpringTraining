package org.caskol.warcraft_database.api.v1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class SpecDTO {
    private Integer id;
    @NotBlank(message = "Это поле не может быть пустым")
    @Size(min = 4, message = "Это поле должно содержать как минимум 4 символа")
    private String name;
    private String description;
    private RoleDTO role;
    private WarcraftClassDTO warcraftClass;
    private IconDTO icon;
    private List<StatDTO> stats;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecDTO specDTO)) return false;
        return Objects.equals(id, specDTO.id) && Objects.equals(name, specDTO.name) && Objects.equals(description, specDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
