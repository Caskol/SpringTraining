package org.caskol.warcraft_database.repositories;

import org.caskol.warcraft_database.models.Spec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecRepository extends JpaRepository<Spec, Integer> {
}
