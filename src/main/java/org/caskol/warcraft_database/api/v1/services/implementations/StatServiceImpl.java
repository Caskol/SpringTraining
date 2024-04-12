package org.caskol.warcraft_database.api.v1.services.implementations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.caskol.warcraft_database.api.v1.dto.StatDTO;
import org.caskol.warcraft_database.api.v1.repositories.StatRepository;
import org.caskol.warcraft_database.api.v1.services.StatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
@Setter
@Service
@Transactional(readOnly = true)
public class StatServiceImpl implements StatService {
    private final StatRepository statRepository;
    public Optional<StatDTO> getById(int id)
    {
        return null;
        //return statRepository.findById(id);
    }
    @Transactional(readOnly = false)
    public void save(StatDTO stat)
    {
        //statRepository.save(stat);
    }
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        statRepository.deleteById(id);
    }
    public List<StatDTO> getAll()
    {
        return null;
        //return statRepository.findAll();
    }
}
