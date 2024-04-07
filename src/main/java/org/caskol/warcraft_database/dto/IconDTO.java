package org.caskol.warcraft_database.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class IconDTO {
    @NonNull
    private String name;
    @NonNull
    private String path;
}
