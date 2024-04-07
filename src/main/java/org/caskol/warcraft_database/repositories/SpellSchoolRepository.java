package org.caskol.warcraft_database.repositories;

import org.caskol.warcraft_database.models.SpellSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellSchoolRepository extends JpaRepository<SpellSchool, Integer> {
}
