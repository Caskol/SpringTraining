package org.caskol.warcraft_database.api.v1.services;

import org.caskol.warcraft_database.api.v1.dto.SpecDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SpecService {
    SpecDTO getById(int id);
    void update(SpecDTO specDTO);
    SpecDTO create(SpecDTO specDTO);
    void delete(int id);
    List<SpecDTO> getAll(Pageable pageable);
}
