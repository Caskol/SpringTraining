package org.caskol.warcraft_database.repositories;

import org.caskol.warcraft_database.models.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatRepository extends JpaRepository<Stat, Integer> {
}