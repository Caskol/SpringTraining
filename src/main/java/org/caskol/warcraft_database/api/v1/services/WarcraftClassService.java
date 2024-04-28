package org.caskol.warcraft_database.api.v1.services;

import org.caskol.warcraft_database.api.v1.dto.WarcraftClassDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WarcraftClassService {
    WarcraftClassDTO getById(int id);
    void update(WarcraftClassDTO warcraftClassDTO);
    WarcraftClassDTO create(WarcraftClassDTO warcraftClassDTO);
    void delete(int id);
    List<WarcraftClassDTO> getAll(Pageable pageable);
}
