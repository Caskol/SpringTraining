package org.caskol.warcraft_database.api.v1.services.implementations;

import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.SpecDTO;
import org.caskol.warcraft_database.api.v1.dto.StatDTO;
import org.caskol.warcraft_database.api.v1.mappers.StatMapper;
import org.caskol.warcraft_database.api.v1.models.Spec;
import org.caskol.warcraft_database.api.v1.models.Stat;
import org.caskol.warcraft_database.api.v1.repositories.SpecRepository;
import org.caskol.warcraft_database.api.v1.repositories.StatRepository;
import org.caskol.warcraft_database.api.v1.services.StatService;
import org.caskol.warcraft_database.utils.RepositoryUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class StatServiceImpl implements StatService {
    private final StatRepository statRepository;
    private final StatMapper statMapper;
    private final Validator validator;
    private final SpecRepository specRepository;

    @Override
    public StatDTO getById(int id)
    {
        return statMapper.allDataToDto(RepositoryUtils.getOneFromRepository(statRepository,id,Stat.class));
    }
    @Override
    @Transactional(readOnly = false)
    public void update(StatDTO statDTO) {
        Stat stat = RepositoryUtils.getOneFromRepository(statRepository,statDTO.getId(),Stat.class);
        establishConnection(statDTO,stat);
        statMapper.partialUpdate(statDTO,stat);
        statRepository.save(stat);
    }
    @Override
    @Transactional(readOnly = false)
    public StatDTO create(StatDTO statDTO) {
        Stat newStat = statMapper.toEntity(statDTO);
        establishConnection(statDTO,newStat);
        statRepository.save(newStat);
        return statMapper.allDataToDto(newStat);
    }
    @Override
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        statRepository.deleteById(id);
    }
    @Override
    public List<StatDTO> getAll(Pageable pageable)
    {
        return statRepository.findAll(pageable)
                .stream()
                .map(statMapper::basicDataToDto)
                .collect(Collectors.toList());
    }
    private void establishConnection(StatDTO statDTO, Stat stat){
        //Bidirectional ManyToMany
        if (statDTO.getSpecs()!=null){
            var specIdsFromDto = statDTO.getSpecs().stream()
                    .map(SpecDTO::getId)
                    .collect(Collectors.toSet());
            Collection<Spec> specs = specRepository.findAllById(specIdsFromDto);
            var idsFromDatabase = specs.stream()
                    .map(Spec::getId)
                    .collect(Collectors.toSet());
            if (RepositoryUtils.isClientIdsValid(idsFromDatabase, specIdsFromDto, Stat.class)){
                if (stat.getId()!=null){
                    stat.getSpecs().forEach(spec->spec.getStats().remove(stat));
                }
                specs.forEach(spec->spec.getStats().add(stat));
                stat.setSpecs(new LinkedHashSet<>(specs));
            }

        }
    }
}
