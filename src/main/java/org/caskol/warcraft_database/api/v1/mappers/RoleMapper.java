package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.RoleDTO;
import org.caskol.warcraft_database.api.v1.models.Role;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
uses = {SpecWithoutListsMapper.class, IconMapper.class})
public interface RoleMapper {
    @Named("AllRoleDataToDTO")
    @Mapping(target = "specs", qualifiedByName = "ListOfBasicSpecDTOData")
    RoleDTO allDataToRoleDTO(Role role);
    @Mapping(target = "id", ignore = true)
    Role toRole(RoleDTO roleDTO);
    @Mapping(target = "id", ignore = true)
    void updateRoleFromDTO(RoleDTO roleDTO, @MappingTarget Role role);
}
