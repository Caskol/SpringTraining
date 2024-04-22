package org.caskol.warcraft_database.api.v1.services.implementations;

import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.RoleDTO;
import org.caskol.warcraft_database.api.v1.dto.SpecDTO;
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

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
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
    public RoleDTO getById(int id) {
        return roleMapper.allDataToDto(RepositoryUtils.getOneFromRepository(roleRepository,id,Role.class));
    }
    @Override
    @Transactional(readOnly = false)
    public void update(RoleDTO roleDTO) {
        Role role = RepositoryUtils.getOneFromRepository(roleRepository,roleDTO.getId(),Role.class);
        roleMapper.partialUpdate(roleDTO,role);
        establishRelation(roleDTO,role);
        roleRepository.save(role);
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
    private void establishRelation(RoleDTO roleDTO, Role role){
        //Bidirectional OneToMany
        if (roleDTO.getSpecs()!=null){
            if (role.getSpecs()==null)
                role.setSpecs(new LinkedHashSet<>());
            role.getSpecs().forEach(spec->spec.setRole(null)); //убираем связи для всех элементов списка

            var specsIdFromDto = roleDTO.getSpecs().stream().map(SpecDTO::getId).collect(Collectors.toSet());
            Collection<Spec> specsFromDatabase = specRepository.findAllById(specsIdFromDto);
            var idsFromDatabase = specsFromDatabase.stream().map(Spec::getId).collect(Collectors.toSet());
            if (RepositoryUtils.isClientIdsValid(idsFromDatabase, specsIdFromDto,Spec.class)){
                specsFromDatabase.forEach(spec->spec.setRole(role));
                role.setSpecs(new LinkedHashSet<>(specsFromDatabase));
            }
        }
        //Unidirectional OneToOne
        if (roleDTO.getIcon()!=null){
            role.setIcon(RepositoryUtils.getOneFromRepository(iconRepository,roleDTO.getIcon().getId(), Icon.class));
        }
    }
}
