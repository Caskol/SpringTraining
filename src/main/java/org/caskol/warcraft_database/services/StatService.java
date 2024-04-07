package org.caskol.warcraft_database.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.caskol.warcraft_database.models.Stat;
import org.caskol.warcraft_database.repositories.StatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
@Setter
@Service
@Transactional(readOnly = true)
public class StatService {
    private final StatRepository statRepository;
    public Optional<Stat> getName(int id)
    {
        return statRepository.findById(id);
    }
    @Transactional(readOnly = false)
    public void save(Stat stat)
    {
        statRepository.save(stat);
    }
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        statRepository.deleteById(id);
    }
    public List<Stat> getAll()
    {
        return statRepository.findAll();
    }
}
