package org.caskol.warcraft_database.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.caskol.warcraft_database.utils.BaseEntity;
import org.springframework.stereotype.Component;

@Entity
@Component
@NoArgsConstructor
@Table(name = "spell_schools")
public class SpellSchool extends BaseEntity {
    public SpellSchool(@NotBlank(message = "Это поле не может быть пустым") @Min(value = 4, message = "Это поле должно содержать как минимум 4 символа") @NonNull String name) {
        super(name);
    }
}
