package org.caskol.warcraft_database.api.v1.services;

import org.caskol.warcraft_database.api.v1.dto.RoleDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    RoleDTO getById(int id);
    void update(RoleDTO roleDTO);
    RoleDTO create(RoleDTO roleDTO);
    void delete(int id);
    List<RoleDTO> getAll(Pageable pageable);
}
