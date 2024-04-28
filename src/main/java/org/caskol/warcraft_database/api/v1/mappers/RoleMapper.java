package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.IconDTO;
import org.caskol.warcraft_database.api.v1.dto.RoleDTO;
import org.caskol.warcraft_database.api.v1.dto.SpecDTO;
import org.caskol.warcraft_database.api.v1.dto.WarcraftClassDTO;
import org.caskol.warcraft_database.api.v1.models.Icon;
import org.caskol.warcraft_database.api.v1.models.Role;
import org.caskol.warcraft_database.api.v1.models.Spec;
import org.caskol.warcraft_database.api.v1.models.WarcraftClass;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface RoleMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name", ignore = false)
    Role toEntity(RoleDTO roleDTO);

    @Named("AllDataToDTO")
    RoleDTO allDataToDto(Role role);

    @Named("DataWithoutList")
    @Mapping(target = "specs", ignore = true)
    RoleDTO basicDataToDto(Role role);

    IconDTO toIconDto(Icon icon);
    Set<SpecDTO> toSpecDtoList(Set<Spec> specList);
    @Mapping(target="stats", ignore = true)
    @Mapping(target = "role", ignore = true)
    SpecDTO toSpecDto(Spec spec);

    @Mapping(target = "specs", ignore = true)
    @Mapping(target="classResources", ignore = true)
    WarcraftClassDTO toWarcraftClassDto(WarcraftClass warcraftClass);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "icon", ignore = true)
    @Mapping(target = "specs", ignore = true)
    @Mapping(target = "id", ignore = true)
    Role partialUpdate(RoleDTO roleDTO, @MappingTarget Role role);
}