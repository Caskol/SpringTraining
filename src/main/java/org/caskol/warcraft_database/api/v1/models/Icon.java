package org.caskol.warcraft_database.api.v1.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "icons")
public class Icon{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "{validation.cannot_be_blank}")
    @Column(name = "name")
    @Size(min = 4, message = "{validation.invalid_size}")
    private String name;
    @NotBlank(message = "{validation.cannot_be_blank}")
    @Column(name = "icon_path")
    private String path;
}
