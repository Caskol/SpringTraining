package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.ClassResourceDTO;
import org.caskol.warcraft_database.api.v1.dto.WarcraftClassDTO;
import org.caskol.warcraft_database.api.v1.models.ClassResource;
import org.caskol.warcraft_database.api.v1.models.WarcraftClass;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClassResourceMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "warcraftClassList", ignore = true)
    ClassResource toEntity(ClassResourceDTO classResourceDTO);
    @Named("AllDataToDTO")
    @Mapping(source = "warcraftClassList", target = "classes")
    ClassResourceDTO allDataToDto(ClassResource classResource);
    @Named("BasicDataToDTO")
    @Mapping(target = "classes", source = "warcraftClassList", ignore = true)
    ClassResourceDTO basicDataToDto(ClassResource classResource);
    List<WarcraftClassDTO> toWarcraftClassDTOList(List<WarcraftClass> warcraftClasses);
    @Mapping(target = "specs", ignore = true)
    @Mapping(target = "classResources", ignore = true)
    WarcraftClassDTO toWarcraftClassDTO(WarcraftClass warcraftClass);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "warcraftClassList",source = "classes",ignore = true)
    ClassResource partialUpdate(ClassResourceDTO classResourceDTO, @MappingTarget ClassResource classResource);
}