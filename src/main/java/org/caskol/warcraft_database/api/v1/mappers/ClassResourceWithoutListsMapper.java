package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.ClassResourceDTO;
import org.caskol.warcraft_database.api.v1.models.ClassResource;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClassResourceWithoutListsMapper {
    @Named("ClassResourceDTOWithoutLists")
    @Mapping(target = "classes", source="warcraftClassList", ignore = true)
    ClassResourceDTO dataWithoutListToDTO(ClassResource classResource);
    @Mapping(target = "id", ignore = true)
    @InheritInverseConfiguration
    @Named("ClassResourceWithoutLists")
    ClassResource toClassResource(ClassResourceDTO classResourceDTO);
    @Named("ListOfBasicClassResourceData")
    //Игнорируем входящий от клиента ID в DTO
    @IterableMapping(qualifiedByName = "ClassResourceWithoutLists")
    List<ClassResource> mapToClassResourceList(List<ClassResourceDTO> classResourceDTOList);
    @Named("ListOfBasicClassResourceDTOData")
    @IterableMapping(qualifiedByName = "ClassResourceDTOWithoutLists")
    List<ClassResourceDTO> mapToClassResourceDTOList(List<ClassResource> classResourceList);

}
