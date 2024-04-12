package org.caskol.warcraft_database.api.v1.services;

import org.caskol.warcraft_database.api.v1.dto.StatDTO;

import java.util.List;
import java.util.Optional;

public interface StatService {
    public Optional<StatDTO> getById(int id);
    public void save(StatDTO statDTO);
    public void delete(int id);
    public List<StatDTO> getAll();
}
