package org.caskol.warcraft_database.api.v1.repositories;

import org.caskol.warcraft_database.api.v1.models.WarcraftClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarcraftClassRepository extends JpaRepository<WarcraftClass, Integer> {
}
