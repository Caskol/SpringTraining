package org.caskol.warcraft_database.api.v1.services.implementations;

import jakarta.validation.ValidationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.caskol.warcraft_database.api.v1.dto.SpellSchoolDTO;
import org.caskol.warcraft_database.api.v1.exceptions.NoSuchElementFoundException;
import org.caskol.warcraft_database.api.v1.mappers.SpellSchoolMapper;
import org.caskol.warcraft_database.api.v1.models.SpellSchool;
import org.caskol.warcraft_database.api.v1.repositories.SpellSchoolRepository;
import org.caskol.warcraft_database.api.v1.services.SpellSchoolService;
import org.caskol.warcraft_database.utils.RestExceptionHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
@Setter
@Service
@Transactional(readOnly = true)
public class SpellSchoolServiceImpl implements SpellSchoolService {
    private final SpellSchoolRepository spellSchoolRepository;
    
    private final SpellSchoolMapper spellSchoolMapper;
    private final Validator validator;
    @Transactional(readOnly = false)
    public SpellSchoolDTO create(SpellSchoolDTO spellSchoolDTO) {
        SpellSchool newSpellSchool = spellSchoolMapper.toSpellSchool(spellSchoolDTO);
        spellSchoolRepository.save(newSpellSchool);
        return spellSchoolMapper.toSpellSchoolDTO(newSpellSchool);
    }

    public SpellSchoolDTO getById(int id)
    {
        Optional<SpellSchool> icon = spellSchoolRepository.findById(id);
        if (icon.isPresent())
            return spellSchoolMapper.toSpellSchoolDTO(icon.get());
        else
            throw new NoSuchElementFoundException(SpellSchool.class.getSimpleName()+ " with id="+id+" was not found");
    }
    @Transactional(readOnly = false)
    public void update(SpellSchoolDTO spellSchoolDTO)
    {
        Optional<SpellSchool> iconFromRepo = spellSchoolRepository.findById(spellSchoolDTO.getId());
        SpellSchool icon = iconFromRepo.orElseThrow(() -> new NoSuchElementFoundException(SpellSchool.class.getSimpleName()+ " with id="+spellSchoolDTO.getId()+" was not found"));
        spellSchoolMapper.updateSpellSchoolFromDto(spellSchoolDTO,icon);
        Errors errors = validator.validateObject(icon);
        if (errors.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG + RestExceptionHandler.getValidationErrorString(errors));
        spellSchoolRepository.save(icon);
    }
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        if (!spellSchoolRepository.findById(id).isPresent())
            throw new NoSuchElementFoundException(SpellSchool.class.getSimpleName()+ " with id="+ id+ " was not found");
        spellSchoolRepository.deleteById(id);
    }
    public List<SpellSchoolDTO> getAll()
    {
        return spellSchoolRepository.findAll()
                .stream()
                .map(spellSchoolMapper::toSpellSchoolDTO)
                .toList();
    }
}
