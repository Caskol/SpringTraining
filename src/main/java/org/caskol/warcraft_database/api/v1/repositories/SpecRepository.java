package org.caskol.warcraft_database.api.v1.repositories;

import org.caskol.warcraft_database.api.v1.models.Spec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecRepository extends JpaRepository<Spec, Integer> {
}
