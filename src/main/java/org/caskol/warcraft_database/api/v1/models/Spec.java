package org.caskol.warcraft_database.api.v1.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="specs")
public class Spec{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Это поле не может быть пустым")
    @Column(name = "name")
    @Size(min = 4, message = "Это поле должно содержать как минимум 4 символа")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private WarcraftClass warcraftClass;

    @OneToOne
    @JoinColumn(name = "icon_id", referencedColumnName = "id")
    private Icon icon;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
    fetch = FetchType.LAZY)
    @JoinTable(
            name = "specs_stats",
            joinColumns = @JoinColumn(name = "spec_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "stat_id", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"spec_id", "stat_id"})
    )
    @Fetch(FetchMode.SUBSELECT)
    private Set<Stat> stats;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spec spec)) return false;
        return Objects.equals(id, spec.id) && Objects.equals(name, spec.name) && Objects.equals(description, spec.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
