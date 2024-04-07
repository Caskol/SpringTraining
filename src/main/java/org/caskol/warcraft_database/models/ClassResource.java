package org.caskol.warcraft_database.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.caskol.warcraft_database.utils.BaseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@NoArgsConstructor
@Component
@Table(name = "class_resources")
public class ClassResource extends BaseEntity {
    @ManyToMany
    @JoinTable(
            name = "classresources_class",
            joinColumns = @JoinColumn(name = "class_resource_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "class_id", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"class_resource_id", "class_id"})
    )
    private List<Class> classList;
    public ClassResource(@NotBlank(message = "Это поле не может быть пустым") @Min(value = 4, message = "Это поле должно содержать как минимум 4 символа") @NonNull String name) {
        super(name);
    }
}
