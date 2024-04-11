package org.caskol.warcraft_database.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.caskol.warcraft_database.utils.BaseDTO;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class WarcraftClassDTO{
    private int id;
    private int name;
    private String description;
    private IconDTO icon;
    private Set<ClassResourceDTO> classResources;
}
