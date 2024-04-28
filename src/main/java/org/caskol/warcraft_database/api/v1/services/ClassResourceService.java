package org.caskol.warcraft_database.api.v1.services;

import org.caskol.warcraft_database.api.v1.dto.ClassResourceDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClassResourceService {
    ClassResourceDTO getById(int id);
    void update(ClassResourceDTO classResourceDTO);
    ClassResourceDTO create(ClassResourceDTO classResourceDTO);
    void delete(int id);
    List<ClassResourceDTO> getAll(Pageable pageable);
}
