package org.caskol.warcraft_database.api.v1.services.implementations;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.SpecDTO;
import org.caskol.warcraft_database.api.v1.exceptions.NoSuchElementFoundException;
import org.caskol.warcraft_database.api.v1.mappers.SpecMapper;
import org.caskol.warcraft_database.api.v1.mappers.SpecWithoutListsMapper;
import org.caskol.warcraft_database.api.v1.models.Icon;
import org.caskol.warcraft_database.api.v1.models.Role;
import org.caskol.warcraft_database.api.v1.models.Spec;
import org.caskol.warcraft_database.api.v1.models.WarcraftClass;
import org.caskol.warcraft_database.api.v1.repositories.IconRepository;
import org.caskol.warcraft_database.api.v1.repositories.RoleRepository;
import org.caskol.warcraft_database.api.v1.repositories.SpecRepository;
import org.caskol.warcraft_database.api.v1.repositories.WarcraftClassRepository;
import org.caskol.warcraft_database.api.v1.services.SpecService;
import org.caskol.warcraft_database.utils.RestExceptionHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SpecServiceImpl implements SpecService {
    private final SpecRepository specRepository;
    private final IconRepository iconRepository;
    private final WarcraftClassRepository warcraftClassRepository;
    private final RoleRepository roleRepository;
    private final SpecMapper specMapper;
    private final SpecWithoutListsMapper specWithoutListsMapper;
    private final Validator validator;
    public SpecDTO getById(int id)
    {
        Optional<Spec> spec= specRepository.findById(id);
        if (!spec.isPresent())
            throw new NoSuchElementFoundException(Spec.class.getSimpleName()+" with id="+id+" was not found");
        return specMapper.allDataToSpecDTO(spec.get());
    }
    @Transactional(readOnly = false)
    public void update(SpecDTO specDTO) {
        Optional<Spec> specFromDatabase = specRepository.findById(specDTO.getId());
        if (!specFromDatabase.isPresent())
            throw new NoSuchElementFoundException(Spec.class.getSimpleName()+" with id="+specDTO.getId()+" was not found.");
        specMapper.updateSpecFromDTO(specDTO,specFromDatabase.get());
        Errors errors = validator.validateObject(specFromDatabase.get());
        if (errors.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG+RestExceptionHandler.getValidationErrorString(errors));
        specRepository.save(specFromDatabase.get());
    }
    @Transactional(readOnly = false)
    public SpecDTO create(SpecDTO specDTO) {
        Spec newSpec = specMapper.toSpec(specDTO);
        Optional<Icon> icon = iconRepository.findById(newSpec.getIcon().getId());
        Optional<Role> role = roleRepository.findById(newSpec.getRole().getId());
        Optional<WarcraftClass> warcraftClass = warcraftClassRepository.findById(newSpec.getWarcraftClass().getId());
        //Role role = newSpec.getRole();
        //WarcraftClass warcraftClass = newSpec.getWarcraftClass();
        newSpec.setWarcraftClass(warcraftClass.get());
        newSpec.setRole(role.get());
        newSpec.setIcon(icon.get());
        specRepository.save(newSpec);
        return specMapper.allDataToSpecDTO(newSpec);
    }
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        specRepository.deleteById(id);
    }
    public List<SpecDTO> getAll()
    {
        return specRepository.findAll()
                .stream()
                .map(specWithoutListsMapper::dataWithoutListToSpecDTO)
                .collect(Collectors.toList());
    }
}
