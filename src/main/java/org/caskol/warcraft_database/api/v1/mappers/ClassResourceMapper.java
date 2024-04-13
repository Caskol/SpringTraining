package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.ClassResourceDTO;
import org.caskol.warcraft_database.api.v1.dto.WarcraftClassDTO;
import org.caskol.warcraft_database.api.v1.models.ClassResource;
import org.caskol.warcraft_database.api.v1.models.WarcraftClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClassResourceMapper {
    @Mapping(target = "classes", source="warcraftClassList")
    ClassResourceDTO allDataToDTO(ClassResource classResource);
    @Mapping(target = "classes", source="warcraftClassList", ignore = true)
    ClassResourceDTO dataWithoutListToDTO(ClassResource classResource);
    //Игнорируем входящий от клиента ID в DTO
    @Mapping(target = "id", ignore = true)
    @Mapping(target="warcraftClassList", source = "classes")
    void updateClassResourceFromDto(ClassResourceDTO classResourceDTO, @MappingTarget ClassResource classResource);
    @Mapping(target = "id", ignore = true)
    @Mapping(target="warcraftClassList", source = "classes")
    ClassResource toClassResource(ClassResourceDTO classResourceDTO);
    List<WarcraftClassDTO> mapEntityToWarcraftClassDTO(List<WarcraftClass> classes);
    List<WarcraftClass> mapWarcraftClassDTOToEntity(List<WarcraftClassDTO> classes);

}
