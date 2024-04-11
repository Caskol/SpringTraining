package org.caskol.warcraft_database.dto;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class IconDTO {
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String path;
}
