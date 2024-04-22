package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.ClassResourceDTO;
import org.caskol.warcraft_database.api.v1.dto.IconDTO;
import org.caskol.warcraft_database.api.v1.dto.SpecDTO;
import org.caskol.warcraft_database.api.v1.dto.WarcraftClassDTO;
import org.caskol.warcraft_database.api.v1.models.ClassResource;
import org.caskol.warcraft_database.api.v1.models.Icon;
import org.caskol.warcraft_database.api.v1.models.Spec;
import org.caskol.warcraft_database.api.v1.models.WarcraftClass;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface WarcraftClassMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "specs", ignore = true)
    @Mapping(target = "classResourceList", ignore = true)
    @Mapping(target = "icon", ignore = true)
    WarcraftClass toEntity(WarcraftClassDTO warcraftClassDTO);
    @Named("AllDataToDTO")
    WarcraftClassDTO allDataToDto(WarcraftClass warcraftClass);
    @Named("BasicDataToDTO")
    @Mapping(target = "specs", ignore = true)
    @Mapping(target = "classResources", ignore = true)
    WarcraftClassDTO basicDataToDto(WarcraftClass warcraftClass);

    IconDTO toIconDto(Icon icon);

    @Mapping(target = "classes", ignore = true)
    ClassResourceDTO toClassResourceDto(ClassResource classResource);

    List<ClassResourceDTO> toClassResourceDtoList(List<ClassResource> classResourceList);

    @Mapping(target = "warcraftClass", ignore = true)
    @Mapping(target = "stats", ignore = true)
    @Mapping(target = "role", ignore = true)
    SpecDTO toSpecDto(Spec spec);

    List<SpecDTO> toSpecDtoList(List<Spec> specList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "classResourceList", ignore = true)
    @Mapping(target = "specs", ignore = true)
    @Mapping(target = "icon", ignore = true)
    WarcraftClass partialUpdate(WarcraftClassDTO warcraftClassDTO, @MappingTarget WarcraftClass warcraftClass);
}