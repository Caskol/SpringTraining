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
    ClassResource toEntity(ClassResourceDTO classResourceDTO);

    ClassResourceDTO toDto(ClassResource classResource);
    @IterableMapping(qualifiedByName = "WarcraftClassDTOWithoutLists")
    List<WarcraftClassDTO> toWarcraftClassDTOList(List<WarcraftClass> warcraftClasses);
    @IterableMapping(qualifiedByName = "WarcraftClassWithoutLists")
    List<WarcraftClass> toWarcraftClassList(List<WarcraftClassDTO> warcraftClassDTOList);

    @Named("WarcraftClassWithoutLists")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", ignore = false)
    WarcraftClass toWarcraftClass(WarcraftClassDTO warcraftClassDTO);
    @Named("WarcraftClassDTOWithoutLists")
    @Mapping(target = "specs", ignore = true)
    @Mapping(target = "classResources", ignore = true)
    WarcraftClassDTO toWarcraftClassDTO(WarcraftClass warcraftClass);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ClassResource partialUpdate(ClassResourceDTO classResourceDTO, @MappingTarget ClassResource classResource);
}