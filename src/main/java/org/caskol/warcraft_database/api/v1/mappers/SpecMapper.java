package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.*;
import org.caskol.warcraft_database.api.v1.models.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SpecMapper {
    @Mapping(target = "id", ignore = true)
    Spec toEntity(SpecDTO specDTO);

    SpecDTO toDto(Spec spec);

    @Mapping(target = "specs", ignore = true)
    RoleDTO toRoleDto(Role role);
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", ignore = false)
    Role toRole(RoleDTO roleDTO);
    @Named("StatWithoutLists")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", ignore = false)
    Stat toStat(StatDTO statDTO);
    @Named("StatDTOWithoutLists")
    @Mapping(target = "specs", ignore = true)
    StatDTO toStatDto(Stat stat);
    @IterableMapping(qualifiedByName = "StatWithoutLists")
    List<Stat> toStatList(List<StatDTO> statDTOList);
    @IterableMapping(qualifiedByName = "StatDTOWithoutLists")
    List<StatDTO> toStatDto(List<Stat> statList);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", ignore = false)
    Icon toIcon(IconDTO iconDTO);
    IconDTO toIconDto(Icon icon);


    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", ignore = false)
    WarcraftClass toWarcraftClass(WarcraftClassDTO warcraftClassDTO);

    @Mapping(target = "specs", ignore = true)
    @Mapping(target = "classResources", ignore = true)
    WarcraftClassDTO toWarcraftClassDto(WarcraftClass warcraftClass);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Spec partialUpdate(SpecDTO specDTO, @MappingTarget Spec spec);
}