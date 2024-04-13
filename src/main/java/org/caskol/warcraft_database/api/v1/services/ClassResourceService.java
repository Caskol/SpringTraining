package org.caskol.warcraft_database.api.v1.services;

import org.caskol.warcraft_database.api.v1.dto.ClassResourceDTO;

import java.util.List;

public interface ClassResourceService {
    public ClassResourceDTO getById(int id);
    public void update(ClassResourceDTO classResourceDTO);
    public ClassResourceDTO create(ClassResourceDTO classResourceDTO);
    public void delete(int id);
    public List<ClassResourceDTO> getAll();
}
