package org.caskol.warcraft_database.api.v1.services;

import org.caskol.warcraft_database.api.v1.dto.WarcraftClassDTO;

import java.util.List;
import java.util.Optional;

public interface WarcraftClassService {
    public Optional<WarcraftClassDTO> getById(int id);
    public void save(WarcraftClassDTO warcraftClassDTO);
    public void delete(int id);
    public List<WarcraftClassDTO> getAll();
}
