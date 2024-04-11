package org.caskol.warcraft_database.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.caskol.warcraft_database.utils.BaseEntity;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "icons")
public class Icon{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Это поле не может быть пустым")
    @Column(name = "name")
    @Size(min = 4, message = "Это поле должно содержать как минимум 4 символа")
    private String name;

    @Column(name = "icon_path")
    private String path;

}
