package org.caskol.warcraft_database.api.v1.services.implementations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.caskol.warcraft_database.api.v1.dto.SpellSchoolDTO;
import org.caskol.warcraft_database.api.v1.repositories.SpellSchoolRepository;
import org.caskol.warcraft_database.api.v1.services.SpellSchoolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
@Setter
@Service
@Transactional(readOnly = true)
public class SpellSchoolServiceImpl implements SpellSchoolService {
    private final SpellSchoolRepository spellSchoolRepository;
    public Optional<SpellSchoolDTO> getById(int id)
    {
        return null;
        //return spellSchoolRepository.findById(id);
    }
    @Transactional(readOnly = false)
    public void save(SpellSchoolDTO spellSchoolDTO)
    {
        //spellSchoolRepository.save(spellSchool);
    }
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        spellSchoolRepository.deleteById(id);
    }
    public List<SpellSchoolDTO> getAll()
    {
        return null;
       // return spellSchoolRepository.findAll();
    }
}
