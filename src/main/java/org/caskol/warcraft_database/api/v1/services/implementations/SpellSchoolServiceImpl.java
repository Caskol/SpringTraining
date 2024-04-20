package org.caskol.warcraft_database.api.v1.services.implementations;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.SpellSchoolDTO;
import org.caskol.warcraft_database.api.v1.mappers.SpellSchoolMapper;
import org.caskol.warcraft_database.api.v1.models.SpellSchool;
import org.caskol.warcraft_database.api.v1.repositories.SpellSchoolRepository;
import org.caskol.warcraft_database.api.v1.services.SpellSchoolService;
import org.caskol.warcraft_database.utils.RestExceptionHandler;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

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
        Optional<SpellSchool> spellSchool = spellSchoolRepository.findById(id);
        if (!spellSchool.isPresent())
            throw new EntityNotFoundException(SpellSchool.class.getSimpleName()+ " with id="+id+" was not found");
        return spellSchoolMapper.toDto(spellSchool.get());
    }
    @Override
    @Transactional(readOnly = false)
    public void update(SpellSchoolDTO spellSchoolDTO) {
        Optional<SpellSchool> spellSchoolFromRepo = spellSchoolRepository.findById(spellSchoolDTO.getId());
        if (!spellSchoolFromRepo.isPresent())
            throw new EntityNotFoundException(SpellSchool.class.getSimpleName()+ " with id="+spellSchoolDTO.getId()+" was not found");
        spellSchoolMapper.partialUpdate(spellSchoolDTO,spellSchoolFromRepo.get());
        Errors errors = validator.validateObject(spellSchoolFromRepo.get());
        if (errors.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG + RestExceptionHandler.getValidationErrorString(errors));
        spellSchoolRepository.save(spellSchoolFromRepo.get());
    }
    @Override
    @Transactional(readOnly = false)
    public void delete(int id) {
        if (!spellSchoolRepository.findById(id).isPresent())
            throw new EntityNotFoundException(SpellSchool.class.getSimpleName()+ " with id="+ id+ " was not found");
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
