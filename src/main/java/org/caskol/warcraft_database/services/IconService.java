package org.caskol.warcraft_database.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.caskol.warcraft_database.models.Icon;
import org.caskol.warcraft_database.repositories.IconRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
@Setter
@Service
@Transactional(readOnly = true)
public class IconService {
    private final IconRepository iconRepository;
    public Optional<Icon> getName(int id)
    {
        return iconRepository.findById(id);
    }
    @Transactional(readOnly = false)
    public void save(Icon icon)
    {
        iconRepository.save(icon);
    }
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        iconRepository.deleteById(id);
    }
    public List<Icon> getAll()
    {
        return iconRepository.findAll();
    }
}
