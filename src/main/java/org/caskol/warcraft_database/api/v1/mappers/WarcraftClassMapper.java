package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.WarcraftClassDTO;
import org.caskol.warcraft_database.api.v1.models.WarcraftClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
uses = {SpecWithoutListsMapper.class, ClassResourceWithoutListsMapper.class})
public interface WarcraftClassMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "classResourceList", source = "classResources", qualifiedByName = "ListOfBasicClassResourceData")
    @Mapping(target = "specs", qualifiedByName = "ListOfBasicSpecData")
    WarcraftClass toWarcraftClass(WarcraftClassDTO warcraftClassDTO);
    @Named("AllWarcraftClassDataToDTO")
    @Mapping(target = "specs", qualifiedByName = "ListOfBasicSpecDTOData")
    @Mapping(target = "classResources", source = "classResourceList", qualifiedByName = "ListOfBasicClassResourceDTOData")
    WarcraftClassDTO allDataToWarcraftClassDTO(WarcraftClass warcraftClass);
}
