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
    WarcraftClass toEntity(WarcraftClassDTO warcraftClassDTO);

    @AfterMapping
    default void linkSpecs(@MappingTarget WarcraftClass warcraftClass) {
        warcraftClass.getSpecs().forEach(spec -> spec.setWarcraftClass(warcraftClass));
    }

    WarcraftClassDTO toDto(WarcraftClass warcraftClass);

    IconDTO toIconDto(Icon icon);
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", ignore = false)
    Icon toIcon(IconDTO iconDTO);
    @Named("ClassResourceDTOWithoutLists")
    @Mapping(target = "classes", ignore = true)
    ClassResourceDTO toClassResourceDto(ClassResource classResource);
    @Named("ClassResourceWithoutLists")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", ignore = false)
    ClassResource toClassResource(ClassResourceDTO classResourceDTO);

    @IterableMapping(qualifiedByName = "ClassResourceDTOWithoutLists")
    List<ClassResourceDTO> toClassResourceDtoList(List<ClassResource> classResourceList);
    @IterableMapping(qualifiedByName = "ClassResourceWithoutLists")
    List<ClassResource> toClassResourceList(List<ClassResourceDTO> classResourceDTOList);

    @Named("SpecWithoutLists")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", ignore = false)
    Spec toSpec(SpecDTO specDTO);
    @Named("SpecDTOWithoutLists")
    @Mapping(target = "warcraftClass", ignore = true)
    @Mapping(target = "stats", ignore = true)
    @Mapping(target = "role", ignore = true)
    SpecDTO toSpecDto(Spec spec);
    @IterableMapping(qualifiedByName = "SpecDTOWithoutLists")
    List<SpecDTO> toSpecDtoList(List<Spec> specList);
    @IterableMapping(qualifiedByName = "SpecWithoutLists")
    List<Spec> toSpecList(List<SpecDTO> specDTOList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    WarcraftClass partialUpdate(WarcraftClassDTO warcraftClassDTO, @MappingTarget WarcraftClass warcraftClass);
}