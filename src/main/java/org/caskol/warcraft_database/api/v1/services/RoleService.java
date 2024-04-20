package org.caskol.warcraft_database.api.v1.services;

import org.caskol.warcraft_database.api.v1.dto.RoleDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    public RoleDTO getById(int id);
    public void update(RoleDTO roleDTO);
    public RoleDTO create(RoleDTO roleDTO);
    public void delete(int id);
    public List<RoleDTO> getAll(Pageable pageable);
}
