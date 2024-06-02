package org.caskol.warcraft_database.api.v1.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classes")
public class WarcraftClass{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "{validation.cannot_be_blank}")
    @Column(name = "name")
    @Size(min = 4, message = "{validation.invalid_size}")
    private String name;
    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "icon_id", referencedColumnName = "id")
    private Icon icon;

    @ManyToMany(mappedBy = "warcraftClassList")
    private Set<ClassResource> classResourceList;

    @OneToMany(mappedBy = "warcraftClass")
    private Set<Spec> specs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WarcraftClass that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
