package org.caskol.warcraft_database.api.v1.repositories;

import org.caskol.warcraft_database.api.v1.models.WarcraftClass;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarcraftClassRepository extends JpaRepository<WarcraftClass, Integer> {
    @Query(value = "select wc from WarcraftClass wc left join fetch wc.icon",
            countQuery = "select count(*) from WarcraftClass wc left join fetch wc.icon")
    List<WarcraftClass> findAllFetchWithoutList(Pageable pageable);
}
