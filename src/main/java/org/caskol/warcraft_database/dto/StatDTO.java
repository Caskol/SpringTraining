package org.caskol.warcraft_database.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class StatDTO {
    private int id;
    private String name;
    private Set<SpecDTO> specs;
}
