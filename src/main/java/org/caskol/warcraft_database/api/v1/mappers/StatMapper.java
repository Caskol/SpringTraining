package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.StatDTO;
import org.caskol.warcraft_database.api.v1.models.Stat;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {SpecWithoutListsMapper.class})
public interface StatMapper {
    @Named("AllStatDataToDTO")
    @Mapping(target = "specs", qualifiedByName = "ListOfBasicSpecDTOData")
    StatDTO allDataToStatDTO(Stat stat);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "specs", qualifiedByName = "ListOfBasicSpecData")
    Stat toStat(StatDTO statDTO);
    @Mapping(target = "id", ignore = true)
    void updateStatFromDTO(StatDTO statDTO, @MappingTarget Stat stat);
}
