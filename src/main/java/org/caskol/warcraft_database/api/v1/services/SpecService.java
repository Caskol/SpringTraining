package org.caskol.warcraft_database.api.v1.services;

import org.caskol.warcraft_database.api.v1.dto.SpecDTO;

import java.util.List;
import java.util.Optional;

public interface SpecService {
    public Optional<SpecDTO> getById(int id);
    public void save(SpecDTO specDTO);
    public void delete(int id);
    public List<SpecDTO> getAll();
}
