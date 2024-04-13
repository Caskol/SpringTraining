package org.caskol.warcraft_database.api.v1.services.implementations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.caskol.warcraft_database.api.v1.dto.SpecDTO;
import org.caskol.warcraft_database.api.v1.repositories.SpecRepository;
import org.caskol.warcraft_database.api.v1.services.SpecService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
@Setter
@Service
@Transactional(readOnly = true)
public class SpecServiceImpl implements SpecService {
    private final SpecRepository specRepository;
    public Optional<SpecDTO> getById(int id)
    {
        return specRepository.findById(id);
    }
    @Transactional(readOnly = false)
    public void save(SpecDTO specDTO)
    {
        specRepository.save(specDTO);
    }
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        specRepository.deleteById(id);
    }
    public List<SpecDTO> getAll()
    {
        return specRepository.findAll();
    }
}
