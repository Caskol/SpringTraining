package org.caskol.warcraft_database.api.v1.services.implementations;

import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.SpellSchoolDTO;
import org.caskol.warcraft_database.api.v1.mappers.SpellSchoolMapper;
import org.caskol.warcraft_database.api.v1.models.SpellSchool;
import org.caskol.warcraft_database.api.v1.repositories.SpellSchoolRepository;
import org.caskol.warcraft_database.api.v1.services.SpellSchoolService;
import org.caskol.warcraft_database.utils.RepositoryUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SpellSchoolServiceImpl implements SpellSchoolService {
    private final SpellSchoolRepository spellSchoolRepository;
    private final SpellSchoolMapper spellSchoolMapper;
    private final Validator validator;
    @Override
    @Transactional(readOnly = false)
    public SpellSchoolDTO create(SpellSchoolDTO spellSchoolDTO) {
        SpellSchool newSpellSchool = spellSchoolMapper.toEntity(spellSchoolDTO);
        spellSchoolRepository.save(newSpellSchool);
        return spellSchoolMapper.toDto(newSpellSchool);
    }
    @Override
    public SpellSchoolDTO getById(int id) {
        return spellSchoolMapper.toDto(RepositoryUtils.getObjectFromRepository(spellSchoolRepository,id,SpellSchool.class));
    }
    @Override
    @Transactional(readOnly = false)
    public void update(SpellSchoolDTO spellSchoolDTO) {
        SpellSchool spellSchool = RepositoryUtils.getObjectFromRepository(spellSchoolRepository,spellSchoolDTO.getId(),SpellSchool.class);
        spellSchoolMapper.partialUpdate(spellSchoolDTO,spellSchool);
        spellSchoolRepository.save(spellSchool);
    }
    @Override
    @Transactional(readOnly = false)
    public void delete(int id) {
        spellSchoolRepository.deleteById(id);
    }
    @Override
    public List<SpellSchoolDTO> getAll(Pageable pageable) {
        return spellSchoolRepository.findAll(pageable)
                .stream()
                .map(spellSchoolMapper::toDto)
                .toList();
    }
}
