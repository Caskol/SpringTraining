package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.*;
import org.caskol.warcraft_database.api.v1.models.*;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface WarcraftClassMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "specs", ignore = true)
    @Mapping(target = "classResourceList", ignore = true)
    @Mapping(target = "icon", ignore = true)
    WarcraftClass toEntity(WarcraftClassDTO warcraftClassDTO);
    @Named("AllDataToDTO")
    @Mapping(target = "classResources", source = "classResourceList")
    WarcraftClassDTO allDataToDto(WarcraftClass warcraftClass);
    @Named("BasicDataToDTO")
    @Mapping(target = "specs", ignore = true)
    @Mapping(target = "classResources", ignore = true)
    WarcraftClassDTO basicDataToDto(WarcraftClass warcraftClass);

    IconDTO toIconDto(Icon icon);

    @Mapping(target = "classes", ignore = true)
    ClassResourceDTO toClassResourceDto(ClassResource classResource);

    @Mapping(target = "warcraftClass", ignore = true)
    @Mapping(target = "stats", ignore = true)
    SpecDTO toSpecDto(Spec spec);

    @Mapping(target = "specs", ignore = true)
    RoleDTO toRoleDto(Role role);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "classResourceList", ignore = true)
    @Mapping(target = "specs", ignore = true)
    @Mapping(target = "icon", ignore = true)
    WarcraftClass partialUpdate(WarcraftClassDTO warcraftClassDTO, @MappingTarget WarcraftClass warcraftClass);
}