package org.caskol.warcraft_database.api.v1.services;

import org.caskol.warcraft_database.api.v1.dto.SpellSchoolDTO;

import java.util.List;
import java.util.Optional;

public interface SpellSchoolService {
    public Optional<SpellSchoolDTO> getById(int id);
    public void save(SpellSchoolDTO spellSchoolDTO);
    public void delete(int id);
    public List<SpellSchoolDTO> getAll();
}
