package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.ClassResourceDTO;
import org.caskol.warcraft_database.api.v1.models.ClassResource;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
uses = {WarcraftClassWithoutListsMapper.class})
public interface ClassResourceMapper {
    @Named("AllClassResourceData")
    @Mapping(target = "classes", source = "warcraftClassList", qualifiedByName = "ListOfBasicWarcraftClassDTOData")
    ClassResourceDTO allDataToDTO(ClassResource classResource);
    //Игнорируем входящий от клиента ID в DTO
    @Mapping(target = "id", ignore = true)
    @Mapping(target="warcraftClassList", source = "classes")
    void updateClassResourceFromDto(ClassResourceDTO classResourceDTO, @MappingTarget ClassResource classResource);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "warcraftClassList", source = "classes", qualifiedByName = "ListOfBasicWarcraftClassData")
    ClassResource toClassResource(ClassResourceDTO classResourceDTO);
}
