package org.caskol.warcraft_database.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.caskol.warcraft_database.models.SpellSchool;
import org.caskol.warcraft_database.repositories.SpellSchoolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
@Setter
@Service
@Transactional(readOnly = true)
public class SpellSchoolService{
    private final SpellSchoolRepository spellSchoolRepository;
    public Optional<SpellSchool> getName(int id)
    {
        return spellSchoolRepository.findById(id);
    }
    @Transactional(readOnly = false)
    public void save(SpellSchool spellSchool)
    {
        spellSchoolRepository.save(spellSchool);
    }
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        spellSchoolRepository.deleteById(id);
    }
    public List<SpellSchool> getAll()
    {
        return spellSchoolRepository.findAll();
    }
}
