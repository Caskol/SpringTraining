package org.caskol.warcraft_database.api.v1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WarcraftClassDTO{
    private int id;
    @NotBlank(message = "Это поле не может быть пустым")
    @Size(min = 4, message = "Это поле должно содержать как минимум 4 символа")
    private String name;
    private String description;
    @NotBlank(message = "Это поле не может быть пустым")
    private IconDTO icon;
    private List<ClassResourceDTO> classResources;
    private List<SpecDTO> specs;
}
