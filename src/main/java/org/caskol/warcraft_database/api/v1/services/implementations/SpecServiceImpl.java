package org.caskol.warcraft_database.api.v1.services.implementations;

import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.SpecDTO;
import org.caskol.warcraft_database.api.v1.dto.StatDTO;
import org.caskol.warcraft_database.api.v1.mappers.SpecMapper;
import org.caskol.warcraft_database.api.v1.models.*;
import org.caskol.warcraft_database.api.v1.repositories.*;
import org.caskol.warcraft_database.api.v1.services.SpecService;
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
public class SpecServiceImpl implements SpecService {
    private final SpecRepository specRepository;
    private final IconRepository iconRepository;
    private final WarcraftClassRepository warcraftClassRepository;
    private final RoleRepository roleRepository;
    private final SpecMapper specMapper;
    private final StatRepository statRepository;

    @Override
    public SpecDTO getById(int id) {
         return specMapper.allDataToDto(RepositoryUtils.getOneFromRepository(specRepository,id,Spec.class));
    }
    @Override
    @Transactional(readOnly = false)
    public void update(SpecDTO specDTO) {
        Spec spec = RepositoryUtils.getOneFromRepository(specRepository,specDTO.getId(), Spec.class);
        establishRelation(specDTO,spec);
        specMapper.partialUpdate(specDTO,spec);
        specRepository.save(spec);
    }
    @Override
    @Transactional(readOnly = false)
    public SpecDTO create(SpecDTO specDTO) {
        Spec newSpec = specMapper.toEntity(specDTO);
        establishRelation(specDTO,newSpec);
        specRepository.save(newSpec);
        return specMapper.allDataToDto(newSpec);
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
        return specRepository.findAllFetchWithoutList(pageable)
                .stream()
                .map(specMapper::basicDataToDto)
                .collect(Collectors.toList());
    }

    private void establishRelation(SpecDTO specDTO, Spec spec){
        //Unidirectional OneToOne
        if (specDTO.getIcon()!=null){
            spec.setIcon(RepositoryUtils.getOneFromRepository(iconRepository,specDTO.getIcon().getId(), Icon.class));
        }
        //Bidirectional OneToMany (ManyToOne side)
        if (specDTO.getRole()!=null){
            if (spec.getRole()!=null && spec.getRole().getSpecs()!=null){ //если роль у спека уже есть
                spec.getRole().getSpecs().remove(spec); //удаляем предыдущие связи
            }
            Role role = RepositoryUtils.getOneFromRepository(roleRepository,specDTO.getRole().getId(), Role.class);
            if (role.getSpecs()==null)
                role.setSpecs(new LinkedHashSet<>());
            role.getSpecs().add(spec);
            spec.setRole(role);
        }
        //Bidirectional OneToMany (ManyToOne side)
        if (specDTO.getWarcraftClass()!=null){
            if (spec.getWarcraftClass()!=null && spec.getWarcraftClass().getSpecs()!=null){ //если роль у спека уже есть
                spec.getWarcraftClass().getSpecs().remove(spec); //удаляем предыдущие связи
            }
            WarcraftClass warcraftClass = RepositoryUtils.getOneFromRepository(warcraftClassRepository,specDTO.getWarcraftClass().getId(), WarcraftClass.class);
            if (warcraftClass.getSpecs()==null)
                warcraftClass.setSpecs(new LinkedHashSet<>());
            warcraftClass.getSpecs().add(spec);
            spec.setWarcraftClass(warcraftClass);
        }
        //Bidirectional ManyToMany
        if (specDTO.getStats()!=null){
            var statIdsFromDto = specDTO.getStats().stream()
                    .map(StatDTO::getId)
                    .collect(Collectors.toSet());
            Collection<Stat> stats = statRepository.findAllById(statIdsFromDto);
            var idsFromDatabase = stats.stream()
                    .map(Stat::getId)
                    .collect(Collectors.toSet());
            if (RepositoryUtils.isClientIdsValid(idsFromDatabase, statIdsFromDto, Stat.class)){
                if (spec.getId()!=null){
                    spec.getStats().forEach(stat->stat.getSpecs().remove(spec));
                }
                stats.forEach(stat->stat.getSpecs().add(spec));
                spec.setStats(new LinkedHashSet<>(stats));
            }

        }
    }
}
