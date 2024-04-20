package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.IconDTO;
import org.caskol.warcraft_database.api.v1.models.Icon;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IconMapper {

    @Mapping(target = "id", ignore = true)
    Icon toEntity(IconDTO iconDTO);

    IconDTO toDto(Icon icon);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    Icon partialUpdate(IconDTO iconDTO, @MappingTarget Icon icon);
}