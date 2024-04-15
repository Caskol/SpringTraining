package org.caskol.warcraft_database.api.v1.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "class_resources")
public class ClassResource {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Это поле не может быть пустым")
    @Column(name = "name")
    @Size(min = 4, message = "Это поле должно содержать как минимум 4 символа")
    private String name;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name = "classresources_class",
            joinColumns = @JoinColumn(name = "class_resource_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "class_id", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"class_resource_id", "class_id"})
    )
    private List<WarcraftClass> warcraftClassList;
}
