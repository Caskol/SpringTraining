package org.caskol.warcraft_database.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WarcraftClassDTO {
    private String name;
    private String description;
    private int icon_id;
}
