package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.WarcraftClassDTO;
import org.caskol.warcraft_database.api.v1.models.WarcraftClass;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface WarcraftClassWithoutListsMapper {

    @Named("WarcraftClassDTODataWithoutLists")
    @Mapping(target = "specs", ignore = true)
    @Mapping(target = "classResources", ignore = true)
    WarcraftClassDTO basicDataToWarcraftClassDTO(WarcraftClass warcraftClass);
    @InheritInverseConfiguration
    @Named("WarcraftClassDataWithoutLists")
    WarcraftClass basicDataToWarcraftClass(WarcraftClassDTO warcraftClassDTO);

    @IterableMapping(qualifiedByName = "WarcraftClassDTODataWithoutLists")
    @Named("ListOfBasicWarcraftClassDTOData")
    List<WarcraftClassDTO> mapEntityToWarcraftClassDTO(List<WarcraftClass> classes);
    @IterableMapping(qualifiedByName = "WarcraftClassDataWithoutLists")
    @Named("ListOfBasicWarcraftClassData")
    List<WarcraftClass> mapWarcraftClassDTOToEntity(List<WarcraftClassDTO> classes);
}
