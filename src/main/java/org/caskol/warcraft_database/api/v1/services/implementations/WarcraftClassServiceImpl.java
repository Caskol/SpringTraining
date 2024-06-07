package org.caskol.warcraft_database.api.v1.services.implementations;

import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.ClassResourceDTO;
import org.caskol.warcraft_database.api.v1.dto.SpecDTO;
import org.caskol.warcraft_database.api.v1.dto.WarcraftClassDTO;
import org.caskol.warcraft_database.api.v1.mappers.WarcraftClassMapper;
import org.caskol.warcraft_database.api.v1.models.ClassResource;
import org.caskol.warcraft_database.api.v1.models.Icon;
import org.caskol.warcraft_database.api.v1.models.Spec;
import org.caskol.warcraft_database.api.v1.models.WarcraftClass;
import org.caskol.warcraft_database.api.v1.repositories.ClassResourceRepository;
import org.caskol.warcraft_database.api.v1.repositories.IconRepository;
import org.caskol.warcraft_database.api.v1.repositories.SpecRepository;
import org.caskol.warcraft_database.api.v1.repositories.WarcraftClassRepository;
import org.caskol.warcraft_database.api.v1.services.WarcraftClassService;
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
public class WarcraftClassServiceImpl implements WarcraftClassService {
    private final WarcraftClassRepository warcraftClassRepository;
    private final WarcraftClassMapper warcraftClassMapper;
    private final IconRepository iconRepository;
    private final SpecRepository specRepository;
    private final ClassResourceRepository classResourceRepository;

    @Override
    public WarcraftClassDTO getById(int id) {
        return warcraftClassMapper.allDataToDto(RepositoryUtils.getObjectFromRepository(warcraftClassRepository,id,WarcraftClass.class));
    }
    @Override
    @Transactional(readOnly = false)
    public void update(WarcraftClassDTO warcraftClassDTO) {
        WarcraftClass warcraftClass = RepositoryUtils.getObjectFromRepository(warcraftClassRepository,warcraftClassDTO.getId(),WarcraftClass.class);
        establishConnection(warcraftClassDTO,warcraftClass);
        warcraftClassMapper.partialUpdate(warcraftClassDTO,warcraftClass);
        warcraftClassRepository.save(warcraftClass);
    }
    @Override
    @Transactional(readOnly = false)
    public WarcraftClassDTO create(WarcraftClassDTO warcraftClassDTO) {
        WarcraftClass newWarcraftClass = warcraftClassMapper.toEntity(warcraftClassDTO);
        establishConnection(warcraftClassDTO,newWarcraftClass);
        warcraftClassRepository.save(newWarcraftClass);
        return warcraftClassMapper.allDataToDto(newWarcraftClass);
    }
    @Override
    @Transactional(readOnly = false)
    public void delete(int id) {
        warcraftClassRepository.deleteById(id);
    }
    @Override
    public List<WarcraftClassDTO> getAll(Pageable pageable)
    {
        return warcraftClassRepository.findAllFetchWithoutList(pageable)
                .stream()
                .map(warcraftClassMapper::basicDataToDto)
                .collect(Collectors.toList());
    }
    private void establishConnection(WarcraftClassDTO warcraftClassDTO, WarcraftClass warcraftClass){
        //Bidirectional OneToMany
        if (warcraftClassDTO.getSpecs()!=null){
            var specsIdsFromDto = warcraftClassDTO.getSpecs().stream()
                    .map(SpecDTO::getId)
                    .collect(Collectors.toSet());
            Collection<Spec> specs = specRepository.findAllById(specsIdsFromDto);
            var idsFromDatabase = specs.stream()
                    .map(Spec::getId)
                    .collect(Collectors.toSet());
            if (RepositoryUtils.isClientIdsValid(idsFromDatabase,specsIdsFromDto,Spec.class)){
                specs.forEach(spec->spec.setWarcraftClass(warcraftClass));
                warcraftClass.setSpecs(new LinkedHashSet<>(specs));
            }
        }
        //Unidirectional OneToOne
        if (warcraftClassDTO.getIcon()!=null){
            warcraftClass.setIcon(RepositoryUtils.getObjectFromRepository(iconRepository,warcraftClassDTO.getIcon().getId(), Icon.class));
        }
        //Bidirectional ManyToMany
        if (warcraftClassDTO.getClassResources()!=null){
            var classResourceIdsFromDto = warcraftClassDTO.getClassResources().stream()
                    .map(ClassResourceDTO::getId)
                    .collect(Collectors.toSet());
            Collection<ClassResource> classResources = classResourceRepository.findAllById(classResourceIdsFromDto);
            var idsFromDatabase = classResources.stream()
                    .map(ClassResource::getId)
                    .collect(Collectors.toSet());
            if (RepositoryUtils.isClientIdsValid(idsFromDatabase, classResourceIdsFromDto, ClassResource.class)){
                if (warcraftClass.getId()!=null){
                    warcraftClass.getClassResourceList().forEach(classResource->classResource.getWarcraftClassList().remove(warcraftClass));
                }
                classResources.forEach(classResource->classResource.getWarcraftClassList().add(warcraftClass));
                warcraftClass.setClassResourceList(new LinkedHashSet<>(classResources));
            }

        }
    }
}
