package org.caskol.warcraft_database.api.v1.security.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "security_users")
public class AppUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", length = 30,nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "users_authorities",
            joinColumns = @JoinColumn(name = "security_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "security_role_id", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"security_user_id", "security_role_id"}))
    private Set<AppRole> authorities;

}
