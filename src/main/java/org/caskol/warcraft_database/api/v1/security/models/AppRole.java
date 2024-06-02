package org.caskol.warcraft_database.api.v1.security.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Table(name = "security_roles")
public class AppRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true, length = 50, nullable = false)
    @NotBlank(message = "{validation.cannot_be_blank}")
    private String name;

    @ManyToMany(mappedBy = "authorities")
    private Set<AppUser> users;

    @Override
    public String getAuthority() {
        return name;
    }
}
