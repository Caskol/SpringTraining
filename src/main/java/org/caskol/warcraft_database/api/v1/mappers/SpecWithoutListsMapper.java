package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.SpecDTO;
import org.caskol.warcraft_database.api.v1.models.Spec;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SpecWithoutListsMapper {
    @Named("SpecDataWithoutLists")
    @Mapping(target = "stats", ignore = true)
    @Mapping(target = "warcraftClass", ignore = true)
    @Mapping(target = "role", ignore = true)
    SpecDTO dataWithoutListToSpecDTO(Spec spec);

    @Mapping(target = "id", ignore = true)
    @InheritInverseConfiguration
    @Named("SpecDataWithoutLists")
    Spec dataWithoutListToSpecDTO(SpecDTO specDTO);

    @IterableMapping(qualifiedByName = "SpecDataWithoutLists")
    @Named("ListOfBasicSpecDTOData")
    List<SpecDTO> mapSpecDTOToEntity(List<Spec> specList);
    @InheritInverseConfiguration
    @Named("ListOfBasicSpecData")
    List<Spec> mapEntityToStatDTO(List<SpecDTO> specDTOList);
}
