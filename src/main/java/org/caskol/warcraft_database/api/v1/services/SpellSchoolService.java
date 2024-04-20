package org.caskol.warcraft_database.api.v1.services;

import org.caskol.warcraft_database.api.v1.dto.SpellSchoolDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SpellSchoolService {
    public SpellSchoolDTO getById(int id);
    public void update(SpellSchoolDTO spellSchoolDTO);
    public SpellSchoolDTO create(SpellSchoolDTO spellSchoolDTO);
    public void delete(int id);
    public List<SpellSchoolDTO> getAll(Pageable pageable);
}
