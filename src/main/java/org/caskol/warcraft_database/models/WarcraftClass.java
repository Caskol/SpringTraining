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
public class WarcraftClass extends BaseEntity {
    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "icon_id", referencedColumnName = "id")
    private Icon icon;

    @ManyToMany(mappedBy = "warcraftClassList")
    private List<ClassResource> classResourceList;

    @OneToMany(mappedBy = "warcraftClass")
    private List<Spec> specList;
}
