package org.caskol.warcraft_database.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.caskol.warcraft_database.utils.BaseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Component
@Getter
@Setter
@Table(name = "stats")
public class Stat extends BaseEntity {
    @ManyToMany(mappedBy = "statList")
    private List<Spec> specList;
}
