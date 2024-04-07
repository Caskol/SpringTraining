package org.caskol.warcraft_database.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.caskol.warcraft_database.models.SpellSchool;
import org.caskol.warcraft_database.repositories.SpellSchoolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
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
    public void add(String name)
    {
        spellSchoolRepository.save(new SpellSchool(name));
    }
    public void delete(int id)
    {
        spellSchoolRepository.deleteById(id);
    }
    public void update(SpellSchool spellSchool)
    {
        spellSchoolRepository.save(spellSchool);
    }
    public List<SpellSchool> getAll()
    {
        return spellSchoolRepository.findAll();
    }
}
