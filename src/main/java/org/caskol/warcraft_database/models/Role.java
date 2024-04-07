package org.caskol.warcraft_database.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.caskol.warcraft_database.utils.BaseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
@Table(name = "roles")
public class Role extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "icon_id", referencedColumnName = "id")
    @NonNull
    private Icon icon;

    @OneToMany(mappedBy = "role")
    private List<Spec> specList;

    public Role(@NotBlank(message = "Это поле не может быть пустым") @Min(value = 4, message = "Это поле должно содержать как минимум 4 символа") @NonNull String name, @NonNull Icon icon, List<Spec> specList) {
        super(name);
        this.icon = icon;
        this.specList = specList;
    }
}
