package org.caskol.warcraft_database.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.caskol.warcraft_database.utils.BaseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "classes")
@Component
public class Class extends BaseEntity {
    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "classList")
    private List<ClassResource> classResourceList;

    @OneToMany(mappedBy = "aClass")
    private List<Spec> specList;
}
