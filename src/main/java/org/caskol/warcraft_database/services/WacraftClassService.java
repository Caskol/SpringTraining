package org.caskol.warcraft_database.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.caskol.warcraft_database.models.WarcraftClass;
import org.caskol.warcraft_database.repositories.WarcraftClassRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
@Setter
@Service
@Transactional(readOnly = true)
public class WacraftClassService {
    private final WarcraftClassRepository warcraftClassRepository;
    public Optional<WarcraftClass> getById(int id)
    {
        return warcraftClassRepository.findById(id);
    }
    @Transactional(readOnly = false)
    public void save(WarcraftClass warcraftClass)
    {
        warcraftClassRepository.save(warcraftClass);
    }
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        warcraftClassRepository.deleteById(id);
    }
    public List<WarcraftClass> getAll()
    {
        return warcraftClassRepository.findAll();
    }


}
