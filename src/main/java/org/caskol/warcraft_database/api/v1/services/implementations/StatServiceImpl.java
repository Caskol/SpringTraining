package org.caskol.warcraft_database.api.v1.services.implementations;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.StatDTO;
import org.caskol.warcraft_database.api.v1.mappers.StatMapper;
import org.caskol.warcraft_database.api.v1.models.Stat;
import org.caskol.warcraft_database.api.v1.repositories.StatRepository;
import org.caskol.warcraft_database.api.v1.services.StatService;
import org.caskol.warcraft_database.utils.RestExceptionHandler;
import org.springframework.data.domain.Pageable;
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
public class StatServiceImpl implements StatService {
    private final StatRepository statRepository;
    private final StatMapper statMapper;
    private final Validator validator;
    @Override
    public StatDTO getById(int id)
    {
        Optional<Stat> stat= statRepository.findById(id);
        if (!stat.isPresent())
            throw new EntityNotFoundException(Stat.class.getSimpleName()+" with id="+id+" was not found");
        return statMapper.toDto(stat.get());
    }
    @Override
    @Transactional(readOnly = false)
    public void update(StatDTO statDTO) {
        Optional<Stat> statFromDatabase = statRepository.findById(statDTO.getId());
        if (!statFromDatabase.isPresent())
            throw new EntityNotFoundException(Stat.class.getSimpleName()+" with id="+statDTO.getId()+" was not found.");
        statMapper.partialUpdate(statDTO,statFromDatabase.get());
        Errors errors = validator.validateObject(statFromDatabase.get());
        if (errors.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG+RestExceptionHandler.getValidationErrorString(errors));
        statRepository.save(statFromDatabase.get());
    }
    @Override
    @Transactional(readOnly = false)
    public StatDTO create(StatDTO statDTO) {
        Stat newStat = statMapper.toEntity(statDTO);
        statRepository.save(newStat);
        return statMapper.toDto(newStat);
    }
    @Override
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        statRepository.deleteById(id);
    }
    @Override
    public List<StatDTO> getAll(Pageable pageable)
    {
        return statRepository.findAll(pageable)
                .stream()
                .map(statMapper::toDto)
                .collect(Collectors.toList());
    }
}
