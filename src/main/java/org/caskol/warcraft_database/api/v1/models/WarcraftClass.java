package org.caskol.warcraft_database.api.v1.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private int id;

    @NotBlank(message = "Это поле не может быть пустым")
    @Column(name = "name")
    @Size(min = 4, message = "Это поле должно содержать как минимум 4 символа")
    private String name;
    @Column(name = "description")
    private String description;

    @OneToOne
    @NotNull
    @JoinColumn(name = "icon_id", referencedColumnName = "id")
    private Icon icon;
    @NotNull
    @ManyToMany(mappedBy = "warcraftClassList")
    private List<ClassResource> classResourceList;
    @NotNull
    @OneToMany(mappedBy = "warcraftClass", orphanRemoval = true)
    private List<Spec> specList;
}
