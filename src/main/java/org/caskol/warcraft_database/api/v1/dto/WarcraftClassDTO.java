package org.caskol.warcraft_database.api.v1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class WarcraftClassDTO{
    private Integer id;
    @NotBlank(message = "Это поле не может быть пустым")
    @Size(min = 4, message = "Это поле должно содержать как минимум 4 символа")
    private String name;
    private String description;
    private IconDTO icon;
    private List<ClassResourceDTO> classResources;
    private List<SpecDTO> specs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WarcraftClassDTO that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
