package org.caskol.warcraft_database.api.v1.services.implementations;

import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.IconDTO;
import org.caskol.warcraft_database.api.v1.mappers.IconMapper;
import org.caskol.warcraft_database.api.v1.models.Icon;
import org.caskol.warcraft_database.api.v1.repositories.IconRepository;
import org.caskol.warcraft_database.api.v1.services.IconService;
import org.caskol.warcraft_database.utils.RepositoryUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IconServiceIImpl implements IconService {
    private final IconRepository iconRepository;
    private final IconMapper iconMapper;
    @Override
    @Transactional(readOnly = false)
    public IconDTO create(IconDTO iconDTO) {
        Icon newIcon = iconMapper.toEntity(iconDTO);
        iconRepository.save(newIcon);
        return iconMapper.toDto(newIcon);
    }
    @Override
    public IconDTO getById(int id) {
        return iconMapper.toDto(RepositoryUtils.getObjectFromRepository(iconRepository,id,Icon.class));
    }
    @Override
    @Transactional(readOnly = false)
    public void update(IconDTO iconDTO) {
        Icon icon = RepositoryUtils.getObjectFromRepository(iconRepository,iconDTO.getId(),Icon.class);
        iconMapper.partialUpdate(iconDTO,icon);
        iconRepository.save(icon);
    }
    @Override
    @Transactional(readOnly = false)
    public void delete(int id) {
        iconRepository.deleteById(id);
    }
    @Override
    public List<IconDTO> getAll(Pageable pageable)
    {
        return iconRepository.findAll(pageable)
                .stream()
                .map(iconMapper::toDto)
                .toList();
    }

}
