package org.caskol.warcraft_database.api.v1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WarcraftClassDTO{
    private int id;
    private String name;
    private String description;
    private IconDTO icon;
    private List<ClassResourceDTO> classResources;
    private List<SpecDTO> specs;
}
