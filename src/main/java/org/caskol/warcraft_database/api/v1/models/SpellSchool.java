package org.caskol.warcraft_database.api.v1.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "spell_schools")
public class SpellSchool{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Это поле не может быть пустым")
    @Column(name = "name")
    @Size(min = 4, message = "Это поле должно содержать как минимум 4 символа")
    private String name;
}
