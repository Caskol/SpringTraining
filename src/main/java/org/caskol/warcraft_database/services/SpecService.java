package org.caskol.warcraft_database.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.caskol.warcraft_database.models.Spec;
import org.caskol.warcraft_database.repositories.SpecRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
@Setter
@Service
@Transactional(readOnly = true)
public class SpecService {
    private final SpecRepository specRepository;
    public Optional<Spec> getName(int id)
    {
        return specRepository.findById(id);
    }
    @Transactional(readOnly = false)
    public void save(Spec spec)
    {
        specRepository.save(spec);
    }
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        specRepository.deleteById(id);
    }
    public List<Spec> getAll()
    {
        return specRepository.findAll();
    }
}
