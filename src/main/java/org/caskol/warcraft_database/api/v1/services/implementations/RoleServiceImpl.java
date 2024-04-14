package org.caskol.warcraft_database.api.v1.services.implementations;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.RoleDTO;
import org.caskol.warcraft_database.api.v1.exceptions.NoSuchElementFoundException;
import org.caskol.warcraft_database.api.v1.mappers.RoleMapper;
import org.caskol.warcraft_database.api.v1.models.Role;
import org.caskol.warcraft_database.api.v1.repositories.RoleRepository;
import org.caskol.warcraft_database.api.v1.services.RoleService;
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
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final Validator validator;
    public RoleDTO getById(int id)
    {
        Optional<Role> role= roleRepository.findById(id);
        if (!role.isPresent())
            throw new NoSuchElementFoundException(Role.class.getSimpleName()+" with id="+id+" was not found");
        return roleMapper.allDataToRoleDTO(role.get());
    }
    @Transactional(readOnly = false)
    public void update(RoleDTO roleDTO) {
        Optional<Role> roleFromDatabase = roleRepository.findById(roleDTO.getId());
        if (!roleFromDatabase.isPresent())
            throw new NoSuchElementFoundException(Role.class.getSimpleName()+" with id="+roleDTO.getId()+" was not found.");
        roleMapper.updateRoleFromDTO(roleDTO,roleFromDatabase.get());
        Errors errors = validator.validateObject(roleFromDatabase.get());
        if (errors.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG+RestExceptionHandler.getValidationErrorString(errors));
        roleRepository.save(roleFromDatabase.get());
    }
    @Transactional(readOnly = false)
    public RoleDTO create(RoleDTO roleDTO) {
        Role newRole = roleMapper.toRole(roleDTO);
        roleRepository.save(newRole);
        return roleMapper.allDataToRoleDTO(newRole);
    }
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        roleRepository.deleteById(id);
    }
    public List<RoleDTO> getAll()
    {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::dataWithoutListToRoleDTO)
                .collect(Collectors.toList());
    }
}