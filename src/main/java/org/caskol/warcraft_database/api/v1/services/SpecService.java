package org.caskol.warcraft_database.api.v1.services;

import org.caskol.warcraft_database.api.v1.dto.SpecDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SpecService {
    public SpecDTO getById(int id);
    public void update(SpecDTO specDTO);
    public SpecDTO create(SpecDTO specDTO);
    public void delete(int id);
    public List<SpecDTO> getAll(Pageable pageable);
}
