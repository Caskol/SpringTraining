package org.caskol.warcraft_database.api.v1.services;

import org.caskol.warcraft_database.api.v1.dto.StatDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StatService {
    public StatDTO getById(int id);
    public void update(StatDTO statDTO);
    public StatDTO create(StatDTO statDTO);
    public void delete(int id);
    public List<StatDTO> getAll(Pageable pageable);
}
