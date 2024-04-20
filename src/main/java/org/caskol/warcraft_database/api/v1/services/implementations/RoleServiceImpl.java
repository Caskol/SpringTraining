package org.caskol.warcraft_database.api.v1.services.implementations;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.RoleDTO;
import org.caskol.warcraft_database.api.v1.mappers.RoleMapper;
import org.caskol.warcraft_database.api.v1.models.Icon;
import org.caskol.warcraft_database.api.v1.models.Role;
import org.caskol.warcraft_database.api.v1.models.Spec;
import org.caskol.warcraft_database.api.v1.repositories.IconRepository;
import org.caskol.warcraft_database.api.v1.repositories.RoleRepository;
import org.caskol.warcraft_database.api.v1.repositories.SpecRepository;
import org.caskol.warcraft_database.api.v1.services.RoleService;
import org.caskol.warcraft_database.utils.RepositoryUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final IconRepository iconRepository;
    private final SpecRepository specRepository;
    private final RoleMapper roleMapper;
    @Override
    public RoleDTO getById(int id)
    {
        Optional<Role> role= roleRepository.findById(id);
        if (!role.isPresent())
            throw new EntityNotFoundException(Role.class.getSimpleName()+" with id="+id+" was not found");
        return roleMapper.allDataToDto(role.get());
    }
    @Override
    @Transactional(readOnly = false)
    public void update(RoleDTO roleDTO) {
        Optional<Role> roleFromDatabase = roleRepository.findById(roleDTO.getId());
        if (!roleFromDatabase.isPresent())
            throw new EntityNotFoundException(Role.class.getSimpleName()+" with id="+roleDTO.getId()+" was not found.");
        roleMapper.partialUpdate(roleDTO,roleFromDatabase.get());
        establishRelation(roleDTO,roleFromDatabase.get());
        roleRepository.save(roleFromDatabase.get());
    }
    @Override
    @Transactional(readOnly = false)
    public RoleDTO create(RoleDTO roleDTO) {
        Role newRole = roleMapper.toEntity(roleDTO);
        establishRelation(roleDTO,newRole);
        roleRepository.save(newRole);
        return roleMapper.allDataToDto(newRole);
    }
    @Override
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        roleRepository.deleteById(id);
    }
    @Override
    public List<RoleDTO> getAll(Pageable pageable) {
        return roleRepository.findAllFetchIcon(pageable)
                .stream()
                .map(roleMapper::basicDataToDto)
                .collect(Collectors.toList());
    }
    public void establishRelation(RoleDTO roleDTO, Role role){
        if (roleDTO.getSpecs()!=null){
            if (role.getSpecs()==null)
                role.setSpecs(new LinkedHashSet<>());
            role.getSpecs().forEach(spec->spec.setRole(null)); //убираем связи для всех элементов списка

            role.setSpecs(roleDTO.getSpecs().stream()
                    .map((specDTO)-> RepositoryUtils.getFromRepository(specRepository,specDTO.getId(),Spec.class))
                    .peek(spec->spec.setRole(role))
                    .collect(Collectors.toSet())
            );
        }
        if (roleDTO.getIcon()!=null){
            role.setIcon(RepositoryUtils.getFromRepository(iconRepository,roleDTO.getIcon().getId(), Icon.class));
        }
    }
}
