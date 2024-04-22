package org.caskol.warcraft_database.api.v1.services.implementations;

import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.ClassResourceDTO;
import org.caskol.warcraft_database.api.v1.dto.WarcraftClassDTO;
import org.caskol.warcraft_database.api.v1.mappers.ClassResourceMapper;
import org.caskol.warcraft_database.api.v1.models.ClassResource;
import org.caskol.warcraft_database.api.v1.models.WarcraftClass;
import org.caskol.warcraft_database.api.v1.repositories.ClassResourceRepository;
import org.caskol.warcraft_database.api.v1.repositories.WarcraftClassRepository;
import org.caskol.warcraft_database.api.v1.services.ClassResourceService;
import org.caskol.warcraft_database.utils.RepositoryUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassResourceServiceImpl implements ClassResourceService {
    private final ClassResourceRepository classResourceRepository;
    private final ClassResourceMapper classResourceMapper;
    private final WarcraftClassRepository warcraftClassRepository;

    @Override
    @Transactional(readOnly = false)
    public ClassResourceDTO create(ClassResourceDTO classResourceDTO) {
        ClassResource newClassResource = classResourceMapper.toEntity(classResourceDTO);
        establishConnection(classResourceDTO,newClassResource);
        classResourceRepository.save(newClassResource);
        return classResourceMapper.allDataToDto(newClassResource);
    }
    @Override
    public ClassResourceDTO getById(int id) {
        return classResourceMapper.allDataToDto(RepositoryUtils.getOneFromRepository(classResourceRepository,id,ClassResource.class));
    }
    @Override
    @Transactional(readOnly = false)
    public void update(ClassResourceDTO classResourceDTO) {
        ClassResource classResource = RepositoryUtils.getOneFromRepository(classResourceRepository,classResourceDTO.getId(), ClassResource.class);
        classResourceMapper.partialUpdate(classResourceDTO,classResource);
        establishConnection(classResourceDTO,classResource);
        classResourceRepository.save(classResource);
    }
    @Override
    @Transactional(readOnly = false)
    public void delete(int id) {
        classResourceRepository.deleteById(id);
    }
    @Override
    public List<ClassResourceDTO> getAll(Pageable pageable) {
        return classResourceRepository.findAll(pageable)
                .stream()
                .map(classResourceMapper::basicDataToDto)
                .toList();
    }

    private void establishConnection(ClassResourceDTO classResourceDTO, ClassResource classResource){
        if (classResourceDTO.getClasses()!=null){
            var classIdsFromDto = classResourceDTO.getClasses().stream()
                    .map(WarcraftClassDTO::getId)
                    .collect(Collectors.toSet());
            Collection<WarcraftClass> classes = warcraftClassRepository.findAllById(classIdsFromDto);
            var idsFromDatabase = classes.stream()
                    .map(WarcraftClass::getId)
                    .collect(Collectors.toSet());
            if (RepositoryUtils.isClientIdsValid(idsFromDatabase, classIdsFromDto, WarcraftClass.class))
                classResource.setWarcraftClassList(new LinkedHashSet<>(classes));
        }
    }

}
