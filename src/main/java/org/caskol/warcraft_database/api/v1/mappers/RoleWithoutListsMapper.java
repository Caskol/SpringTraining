package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.RoleDTO;
import org.caskol.warcraft_database.api.v1.models.Role;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleWithoutListsMapper {
    @Named("RoleDTODataWithoutLists")
    @Mapping(target = "specs", ignore = true)
    RoleDTO dataWithoutListToRoleDTO(Role role);
    @Named("RoleDataWithoutLists")
    @InheritInverseConfiguration
    Role dataWithoutListToRole(RoleDTO roleDTO);
}
