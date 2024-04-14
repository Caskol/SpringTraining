package org.caskol.warcraft_database.api.v1.services.implementations;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.IconDTO;
import org.caskol.warcraft_database.api.v1.exceptions.NoSuchElementFoundException;
import org.caskol.warcraft_database.api.v1.mappers.IconMapper;
import org.caskol.warcraft_database.api.v1.models.Icon;
import org.caskol.warcraft_database.api.v1.repositories.IconRepository;
import org.caskol.warcraft_database.api.v1.services.IconService;
import org.caskol.warcraft_database.utils.RestExceptionHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IconServiceIImpl implements IconService {
    private final IconRepository iconRepository;
    private final IconMapper iconMapper;
    private final Validator validator;
    @Transactional(readOnly = false)
    public IconDTO create(IconDTO iconDTO) {
        Icon newIcon = iconMapper.toIcon(iconDTO);
        iconRepository.save(newIcon);
        return iconMapper.toIconDTO(newIcon);
    }

    public IconDTO getById(int id)
    {
        Optional<Icon> icon = iconRepository.findById(id);
        if (!icon.isPresent())
            throw new NoSuchElementFoundException(Icon.class.getSimpleName()+ " with id="+id+" was not found");
        return iconMapper.toIconDTO(icon.get());
    }
    @Transactional(readOnly = false)
    public void update(IconDTO iconDTO) {
        Optional<Icon> iconFromRepo = iconRepository.findById(iconDTO.getId());
        if (!iconFromRepo.isPresent())
            throw new NoSuchElementFoundException(Icon.class.getSimpleName()+ " with id="+iconDTO.getId()+" was not found");
        iconMapper.updateIconFromDto(iconDTO,iconFromRepo.get());
        Errors errors = validator.validateObject(iconFromRepo.get());
        if (errors.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG + RestExceptionHandler.getValidationErrorString(errors));
        iconRepository.save(iconFromRepo.get());
    }
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        if (!iconRepository.findById(id).isPresent())
            throw new NoSuchElementFoundException(Icon.class.getSimpleName()+ " with id="+ id+ " was not found");
        iconRepository.deleteById(id);
    }
    public List<IconDTO> getAll()
    {
        return iconRepository.findAll()
                .stream()
                .map(iconMapper::toIconDTO)
                .toList();
    }

}
