package org.caskol.warcraft_database.api.v1.services.implementations;

import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.ClassResourceDTO;
import org.caskol.warcraft_database.api.v1.repositories.ClassResourceRepository;
import org.caskol.warcraft_database.api.v1.services.ClassResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassResourceServiceImpl implements ClassResourceService {
    private final ClassResourceRepository classResourceRepository;
    public Optional<ClassResourceDTO> getById(int id) {
        return null;
        //return classResourceRepository.findById(id);
    }
    @Transactional(readOnly = false)
    public void save(ClassResourceDTO classResourceDTO) {
        //classResourceRepository.save(classResource);
    }
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        classResourceRepository.deleteById(id);
    }
    public List<ClassResourceDTO> getAll()
    {
        return null;
        //return classResourceRepository.findAll();
    }
}
