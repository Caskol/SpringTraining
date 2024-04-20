package org.caskol.warcraft_database.api.v1.services.implementations;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.SpecDTO;
import org.caskol.warcraft_database.api.v1.mappers.SpecMapper;
import org.caskol.warcraft_database.api.v1.models.Spec;
import org.caskol.warcraft_database.api.v1.repositories.SpecRepository;
import org.caskol.warcraft_database.api.v1.services.SpecService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SpecServiceImpl implements SpecService {
    private final SpecRepository specRepository;
    private final SpecMapper specMapper;
    private final Validator validator;
    @Override
    public SpecDTO getById(int id)
    {
        Optional<Spec> spec= specRepository.findById(id);
        if (!spec.isPresent())
            throw new EntityNotFoundException(Spec.class.getSimpleName()+" with id="+id+" was not found");
        return specMapper.toDto(spec.get());
    }
    @Override
    @Transactional(readOnly = false)
    public void update(SpecDTO specDTO) {
        Optional<Spec> specFromDatabase = specRepository.findById(specDTO.getId());
        if (!specFromDatabase.isPresent())
            throw new EntityNotFoundException(Spec.class.getSimpleName()+" with id="+specDTO.getId()+" was not found.");
        specMapper.partialUpdate(specDTO,specFromDatabase.get());
//        Errors errors = validator.validateObject(specFromDatabase.get());
//        if (errors.hasErrors())
//            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG+RestExceptionHandler.getValidationErrorString(errors));
        specRepository.save(specFromDatabase.get());
    }
    @Override
    @Transactional(readOnly = false)
    public SpecDTO create(SpecDTO specDTO) {
        Spec newSpec = specMapper.toEntity(specDTO);
        specRepository.save(newSpec);
        return specMapper.toDto(newSpec);
    }
    @Override
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        specRepository.deleteById(id);
    }
    @Override
    public List<SpecDTO> getAll(Pageable pageable)
    {
        return specRepository.findAll(pageable)
                .stream()
                .map(specMapper::toDto)
                .collect(Collectors.toList());
    }
}
