package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.SpecDTO;
import org.caskol.warcraft_database.api.v1.dto.StatDTO;
import org.caskol.warcraft_database.api.v1.models.Spec;
import org.caskol.warcraft_database.api.v1.models.Stat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StatMapper {
    StatDTO allDataToStatDTO(Stat stat);
    @Mapping(target = "specList", ignore = true)
    StatDTO dataWithoutListToStatDTO(Stat stat);
    @Mapping(target = "id", ignore = true)
    Stat toStat(StatDTO statDTO);
    @Mapping(target = "id", ignore = true)
    void updateStatFromDTO(StatDTO statDTO, @MappingTarget Stat stat);

    List<SpecDTO> mapSpecDTOToEntity(List<Spec> specList);
    List<Spec> mapEntityToStatDTO(List<SpecDTO> specDTOList);
}
