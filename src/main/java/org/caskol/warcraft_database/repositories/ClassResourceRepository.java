package org.caskol.warcraft_database.repositories;

import org.caskol.warcraft_database.models.ClassResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassResourceRepository extends JpaRepository<ClassResource, Integer> {
}
