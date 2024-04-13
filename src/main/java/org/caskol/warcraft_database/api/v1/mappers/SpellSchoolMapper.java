package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.SpellSchoolDTO;
import org.caskol.warcraft_database.api.v1.models.SpellSchool;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SpellSchoolMapper {
    SpellSchoolDTO toSpellSchoolDTO(SpellSchool spellSchool);
    //Игнорируем входящий от клиента ID в DTO
    @Mapping(target = "id", ignore = true)
    void updateSpellSchoolFromDto(SpellSchoolDTO spellSchoolDTO, @MappingTarget SpellSchool spellSchool);
    @Mapping(target = "id", ignore = true)
    SpellSchool toSpellSchool(SpellSchoolDTO spellSchoolDTO);
}
