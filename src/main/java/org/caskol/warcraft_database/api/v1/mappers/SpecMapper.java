package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.SpecDTO;
import org.caskol.warcraft_database.api.v1.models.Spec;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
uses = {StatWithoutListsMapper.class, WarcraftClassWithoutListsMapper.class, RoleWithoutListsMapper.class})
public interface SpecMapper {
    @Named("AllSpecDataToDTO")
    @Mapping(target = "stats", qualifiedByName = "ListOfBasicStatDTOData")
    @Mapping(target = "warcraftClass", qualifiedByName = "WarcraftClassDTODataWithoutLists")
    @Mapping(target = "role", qualifiedByName = "RoleDTODataWithoutLists")
    SpecDTO allDataToSpecDTO(Spec spec);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stats", qualifiedByName = "ListOfBasicStatData")
    @Mapping(target = "warcraftClass", qualifiedByName = "WarcraftClassDataWithoutLists")
    @Mapping(target = "role", qualifiedByName = "RoleDataWithoutLists")
    Spec toSpec(SpecDTO specDTO);

    @Mapping(target = "id", ignore = true)
    void updateSpecFromDTO(SpecDTO specDTO, @MappingTarget Spec spec);
}
