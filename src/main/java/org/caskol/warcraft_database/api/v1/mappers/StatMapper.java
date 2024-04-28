package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.SpecDTO;
import org.caskol.warcraft_database.api.v1.dto.StatDTO;
import org.caskol.warcraft_database.api.v1.models.Spec;
import org.caskol.warcraft_database.api.v1.models.Stat;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StatMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "specs", ignore = true)
    Stat toEntity(StatDTO statDTO);

    @Named("BasicDataToDTO")
    @Mapping(target = "specs", ignore = true)
    StatDTO basicDataToDto(Stat stat);
    @Named("AllDataToDTO")
    StatDTO allDataToDto(Stat stat);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "stats", ignore = true)
    @Mapping(target = "warcraftClass", ignore = true)
    SpecDTO toSpecDto(Spec spec);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "specs", ignore = true)
    Stat partialUpdate(StatDTO statDTO, @MappingTarget Stat stat);
}