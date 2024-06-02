package org.caskol.warcraft_database.api.v1.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StatDTO {
    private Integer id;
    @NotBlank(message = "{validation.cannot_be_blank}")
    @Size(min = 4, message = "{validation.invalid_size}")
    private String name;
    private List<SpecDTO> specs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatDTO statDTO)) return false;
        return Objects.equals(id, statDTO.id) && Objects.equals(name, statDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
