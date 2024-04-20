package org.caskol.warcraft_database.api.v1.services.implementations;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.ClassResourceDTO;
import org.caskol.warcraft_database.api.v1.mappers.ClassResourceMapper;
import org.caskol.warcraft_database.api.v1.models.ClassResource;
import org.caskol.warcraft_database.api.v1.repositories.ClassResourceRepository;
import org.caskol.warcraft_database.api.v1.services.ClassResourceService;
import org.caskol.warcraft_database.utils.RestExceptionHandler;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassResourceServiceImpl implements ClassResourceService {
    private final ClassResourceRepository classResourceRepository;
    private final ClassResourceMapper classResourceMapper;
    private final Validator validator;
    @Override
    @Transactional(readOnly = false)
    public ClassResourceDTO create(ClassResourceDTO classResourceDTO) {
        ClassResource newClassResource = classResourceMapper.toEntity(classResourceDTO);
        classResourceRepository.save(newClassResource);
        return classResourceMapper.toDto(newClassResource);
    }
    @Override
    public ClassResourceDTO getById(int id) {
        Optional<ClassResource> classResource = classResourceRepository.findById(id);
        if (!classResource.isPresent()) {
            throw new EntityNotFoundException(ClassResource.class.getSimpleName() + " with id=" + id + " was not found");
        }
        return classResourceMapper.toDto(classResource.get());
    }
    @Override
    @Transactional(readOnly = false)
    public void update(ClassResourceDTO classResourceDTO) {
        Optional<ClassResource> classResourceFromRepo = classResourceRepository.findById(classResourceDTO.getId());
        if (!classResourceFromRepo.isPresent())
            throw new EntityNotFoundException(ClassResource.class.getSimpleName()+ " with id="+classResourceDTO.getId()+" was not found");
        classResourceMapper.partialUpdate(classResourceDTO,classResourceFromRepo.get());
        Errors errors = validator.validateObject(classResourceFromRepo.get());
        if (errors.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG + RestExceptionHandler.getValidationErrorString(errors));
        classResourceRepository.save(classResourceFromRepo.get());
    }
    @Override
    @Transactional(readOnly = false)
    public void delete(int id) {
        if (!classResourceRepository.findById(id).isPresent())
            throw new EntityNotFoundException(ClassResource.class.getSimpleName()+ " with id="+ id+ " was not found");
        classResourceRepository.deleteById(id);
    }
    @Override
    public List<ClassResourceDTO> getAll(Pageable pageable) {
        return classResourceRepository.findAll(pageable)
                .stream()
                .map(classResourceMapper::toDto)
                .toList();
    }

}
