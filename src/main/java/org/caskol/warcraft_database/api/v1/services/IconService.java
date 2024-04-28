package org.caskol.warcraft_database.api.v1.services;

import org.caskol.warcraft_database.api.v1.dto.IconDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IconService {
    IconDTO getById(int id);
    void update(IconDTO iconDTO);
    IconDTO create(IconDTO iconDTO);
    void delete(int id);
    List<IconDTO> getAll(Pageable pageable);
}
