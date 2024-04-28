package org.caskol.warcraft_database.api.v1.services;

import org.caskol.warcraft_database.api.v1.dto.StatDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StatService {
    StatDTO getById(int id);
    void update(StatDTO statDTO);
    StatDTO create(StatDTO statDTO);
    void delete(int id);
    List<StatDTO> getAll(Pageable pageable);
}
