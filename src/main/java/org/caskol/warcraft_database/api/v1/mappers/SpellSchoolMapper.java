package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.SpellSchoolDTO;
import org.caskol.warcraft_database.api.v1.models.SpellSchool;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SpellSchoolMapper {
    @Mapping(target = "id", ignore = true)
    SpellSchool toEntity(SpellSchoolDTO spellSchoolDTO);

    SpellSchoolDTO toDto(SpellSchool spellSchool);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SpellSchool partialUpdate(SpellSchoolDTO spellSchoolDTO, @MappingTarget SpellSchool spellSchool);
}