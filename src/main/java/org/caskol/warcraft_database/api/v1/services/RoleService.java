package org.caskol.warcraft_database.api.v1.services;

import org.caskol.warcraft_database.api.v1.dto.RoleDTO;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    public Optional<RoleDTO> getById(int id);
    public void save(RoleDTO roleDTO);
    public void delete(int id);
    public List<RoleDTO> getAll();
}
