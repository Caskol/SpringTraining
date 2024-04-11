package org.caskol.warcraft_database.utils;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@MappedSuperclass
@Data
@AllArgsConstructor
public abstract class BaseEntity {

}
