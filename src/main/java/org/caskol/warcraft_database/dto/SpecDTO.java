package org.caskol.warcraft_database.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpecDTO {
    private String name;
    private String description;
    private int role_id;
    private int class_id;
    private int icon_id;
}
