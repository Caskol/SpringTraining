package org.caskol.warcraft_database.repositories;

import org.caskol.warcraft_database.models.WarcraftClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarcraftClassRepository extends JpaRepository<WarcraftClass, Integer> {
}
