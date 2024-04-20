package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.*;
import org.caskol.warcraft_database.api.v1.models.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SpecMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "icon", ignore = true)
    @Mapping(target = "warcraftClass", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "stats", ignore = true)
    Spec toEntity(SpecDTO specDTO);

    @Named("AllDataToDTO")
    SpecDTO toDto(Spec spec);

    @Named("DataWithoutList")
    @Mapping(target = "stats", ignore = true)
    SpecDTO basicDataToDto(Spec spec);

    @Mapping(target = "specs", ignore = true)
    RoleDTO toRoleDto(Role role);
    @Named("StatDTOWithoutLists")
    @Mapping(target = "specs", ignore = true)
    StatDTO toStatDto(Stat stat);
    @IterableMapping(qualifiedByName = "StatDTOWithoutLists")
    List<StatDTO> toStatDto(List<Stat> statList);

    IconDTO toIconDto(Icon icon);

    @Mapping(target = "specs", ignore = true)
    @Mapping(target = "classResources", ignore = true)
    WarcraftClassDTO toWarcraftClassDto(WarcraftClass warcraftClass);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "stats", ignore = true)
    @Mapping(target = "warcraftClass", ignore = true)
    @Mapping(target = "role", ignore = true)
    Spec partialUpdate(SpecDTO specDTO, @MappingTarget Spec spec);
}