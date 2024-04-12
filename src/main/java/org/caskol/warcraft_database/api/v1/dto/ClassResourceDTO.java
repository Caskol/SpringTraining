package org.caskol.warcraft_database.api.v1.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ClassResourceDTO{
    private int id;
    @NonNull
    private String name;
    private List<WarcraftClassDTO> classes;
}
