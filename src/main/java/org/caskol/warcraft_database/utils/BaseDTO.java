package org.caskol.warcraft_database.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDTO {
    private int id;
    private String name;
}
