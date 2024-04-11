package org.caskol.warcraft_database.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpellSchoolDTO {
    private int id;
    private String name;
}
