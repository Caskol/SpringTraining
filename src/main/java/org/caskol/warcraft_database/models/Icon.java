package org.caskol.warcraft_database.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.caskol.warcraft_database.utils.BaseEntity;
import org.springframework.stereotype.Component;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Component
@Table(name = "icons")
public class Icon extends BaseEntity {
    @Column(name = "icon_path")
    @NonNull
    private String path;

    public Icon(@NotBlank(message = "Это поле не может быть пустым") @Min(value = 4, message = "Это поле должно содержать как минимум 4 символа") @NonNull String name, @NonNull String path) {
        super(name);
        this.path = path;
    }
}
