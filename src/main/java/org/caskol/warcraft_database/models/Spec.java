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
@Table(name="specs")
@Getter
@Setter
@NoArgsConstructor
public class Spec extends BaseEntity {
    @Column(name = "description")
    private String description;

    public Spec(@NotBlank(message = "Это поле не может быть пустым") @Min(value = 4, message = "Это поле должно содержать как минимум 4 символа") @NonNull String name, String description, Role role, WarcraftClass warcraftClass, Icon icon, List<Stat> statList) {
        super(name);
        this.description = description;
        this.role = role;
        this.warcraftClass = warcraftClass;
        this.icon = icon;
        this.statList = statList;
    }

    public Spec(@NotBlank(message = "Это поле не может быть пустым") @Min(value = 4, message = "Это поле должно содержать как минимум 4 символа") @NonNull String name, String description, Role role, WarcraftClass warcraftClass, Icon icon) {
        super(name);
        this.description = description;
        this.role = role;
        this.warcraftClass = warcraftClass;
        this.icon = icon;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private WarcraftClass warcraftClass;

    @OneToOne
    @JoinColumn(name = "icon_id", referencedColumnName = "id")
    private Icon icon;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "spec_stats",
            joinColumns = @JoinColumn(name = "spec_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "stat_id", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"spec_id", "stat_id"})
    )
    private List<Stat> statList;

}
