package org.caskol.warcraft_database.api.v1.security.repositories;

import org.caskol.warcraft_database.api.v1.security.models.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Integer> {
    Optional<AppRole> findByName(String name);
}
