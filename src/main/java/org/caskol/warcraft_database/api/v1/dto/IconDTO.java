package org.caskol.warcraft_database.api.v1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class IconDTO implements Serializable {
    private Integer id;
    @NotBlank(message = "{validation.cannot_be_blank}")
    @Size(min = 4, message = "{validation.invalid_size}")
    private String name;
    @NotNull(message = "{validation.cannot_be_blank}")
    private String path;
}
