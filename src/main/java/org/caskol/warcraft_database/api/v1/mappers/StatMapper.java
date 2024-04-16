package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.SpecDTO;
import org.caskol.warcraft_database.api.v1.dto.StatDTO;
import org.caskol.warcraft_database.api.v1.models.Spec;
import org.caskol.warcraft_database.api.v1.models.Stat;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StatMapper {
    @Mapping(target = "id", ignore = true)
    Stat toEntity(StatDTO statDTO);

    StatDTO toDto(Stat stat);
    @Named("SpecDTOWithoutLists")
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "stats", ignore = true)
    @Mapping(target = "warcraftClass", ignore = true)
    SpecDTO toSpecDto(Spec spec);
    @Named("SpecWithoutLists")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", ignore = false)
    Spec toSpec(SpecDTO specDTO);
    @IterableMapping(qualifiedByName = "SpecDTOWithoutLists")
    List<SpecDTO> toSpecDtoList(List<Spec> specList);
    @IterableMapping(qualifiedByName = "SpecWithoutLists")
    List<Spec> toSpecList(List<SpecDTO> specDTOList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Stat partialUpdate(StatDTO statDTO, @MappingTarget Stat stat);
}