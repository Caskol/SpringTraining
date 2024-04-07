package org.caskol.warcraft_database.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.caskol.warcraft_database.models.ClassResource;
import org.caskol.warcraft_database.repositories.ClassResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
@Setter
@Service
@Transactional(readOnly = true)
public class ClassResourceService {
    private final ClassResourceRepository classResourceRepository;
    public Optional<ClassResource> getName(int id)
    {
        return classResourceRepository.findById(id);
    }
    @Transactional(readOnly = false)
    public void save(ClassResource classResource)
    {
        classResourceRepository.save(classResource);
    }
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        classResourceRepository.deleteById(id);
    }
    public List<ClassResource> getAll()
    {
        return classResourceRepository.findAll();
    }
}
