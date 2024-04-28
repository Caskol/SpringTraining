package org.caskol.warcraft_database.api.v1.services;

import org.caskol.warcraft_database.api.v1.dto.SpellSchoolDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SpellSchoolService {
    SpellSchoolDTO getById(int id);
    void update(SpellSchoolDTO spellSchoolDTO);
    SpellSchoolDTO create(SpellSchoolDTO spellSchoolDTO);
    void delete(int id);
    List<SpellSchoolDTO> getAll(Pageable pageable);
}
