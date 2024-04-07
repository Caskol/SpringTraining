package org.caskol.warcraft_database.repositories;

import org.caskol.warcraft_database.models.Icon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IconRepository extends JpaRepository<Icon, Integer> {
}
