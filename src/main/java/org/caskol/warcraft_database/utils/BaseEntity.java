package org.caskol.warcraft_database.utils;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@MappedSuperclass
@NoArgsConstructor
@RequiredArgsConstructor //если указан @NoArgsConstructor, то RequiredArgsConstructor внутри @Data перестает работать
@Data
public class BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Это поле не может быть пустым")
    @Column(name = "name")
    @Min(value = 4, message = "Это поле должно содержать как минимум 4 символа")
    @NonNull
    private String name;
}
