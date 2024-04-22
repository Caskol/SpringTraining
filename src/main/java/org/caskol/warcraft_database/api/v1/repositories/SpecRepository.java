package org.caskol.warcraft_database.api.v1.repositories;

import org.caskol.warcraft_database.api.v1.models.Spec;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecRepository extends JpaRepository<Spec, Integer> {
    @Query(value = "select s from Spec s left join fetch s.icon left join fetch s.role " +
            "left join fetch s.role.icon left join fetch s.warcraftClass left join fetch s.warcraftClass.icon ",
    countQuery = "select count(*) from Spec s left join fetch s.icon left join fetch s.role " +
            "left join fetch s.role.icon left join fetch s.warcraftClass left join fetch s.warcraftClass.icon")
    List<Spec> findAllFetchWithoutList(Pageable pageable);
}
