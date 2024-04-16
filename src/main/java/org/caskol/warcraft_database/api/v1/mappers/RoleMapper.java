package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.IconDTO;
import org.caskol.warcraft_database.api.v1.dto.RoleDTO;
import org.caskol.warcraft_database.api.v1.dto.SpecDTO;
import org.caskol.warcraft_database.api.v1.models.Icon;
import org.caskol.warcraft_database.api.v1.models.Role;
import org.caskol.warcraft_database.api.v1.models.Spec;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {

    @Mapping(target = "id", ignore = true)
    Role toEntity(RoleDTO roleDTO);

    @AfterMapping
    default void linkSpecs(@MappingTarget Role role) {
        role.getSpecs().forEach(spec -> spec.setRole(role));
    }

    RoleDTO toDto(Role role);

    IconDTO toIconDto(Icon icon);
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", ignore = false)
    Icon toIcon(IconDTO iconDTO);
    @IterableMapping(qualifiedByName = "SpecDTOWithoutLists")
    List<SpecDTO> toSpecDtoList(List<Spec> specList);
    @IterableMapping(qualifiedByName = "SpecWithoutLists")
    List<Spec> toSpecList(List<SpecDTO> specDTOList);
    @Named("SpecDTOWithoutLists")
    @Mapping(target = "warcraftClass", ignore = true)
    @Mapping(target="stats", ignore = true)
    @Mapping(target = "role", ignore = true)
    SpecDTO toSpecDTO(Spec spec);
    @Named("SpecWithoutLists")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", ignore = false)
    Spec toSpec(SpecDTO specDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Role partialUpdate(RoleDTO roleDTO, @MappingTarget Role role);
}