package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.IconDTO;
import org.caskol.warcraft_database.api.v1.models.Icon;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IconMapper {
    IconDTO toIconDTO(Icon icon);
    //Игнорируем входящий от клиента ID в DTO
    @Mapping(target = "id", ignore = true)
    void updateIconFromDto(IconDTO iconDTO, @MappingTarget Icon icon);
    @Mapping(target = "id", ignore = true)
    Icon toIcon(IconDTO iconDTO);
}
