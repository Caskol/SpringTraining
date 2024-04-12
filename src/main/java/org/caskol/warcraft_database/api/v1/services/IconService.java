package org.caskol.warcraft_database.api.v1.services;

import org.caskol.warcraft_database.api.v1.dto.IconDTO;

import java.util.List;

public interface IconService {
    public IconDTO getById(int id);
    public void update(IconDTO iconDTO);
    public IconDTO create(IconDTO iconDTO);
    public void delete(int id);
    public List<IconDTO> getAll();
}
