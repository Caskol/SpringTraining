package org.caskol.warcraft_database.api.v1.services;

import org.caskol.warcraft_database.api.v1.dto.WarcraftClassDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WarcraftClassService {
    public WarcraftClassDTO getById(int id);
    public void update(WarcraftClassDTO warcraftClassDTO);
    public WarcraftClassDTO create(WarcraftClassDTO warcraftClassDTO);
    public void delete(int id);
    public List<WarcraftClassDTO> getAll(Pageable pageable);
}
