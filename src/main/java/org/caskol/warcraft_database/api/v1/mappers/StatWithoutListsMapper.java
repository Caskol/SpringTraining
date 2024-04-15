package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.StatDTO;
import org.caskol.warcraft_database.api.v1.models.Stat;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StatWithoutListsMapper {
    @Named("StatDTODataWithoutLists")
    @Mapping(target = "specs", ignore = true)
    StatDTO dataWithoutListToStatDTO(Stat stat);
    @Named("StatDataWithoutLists")
    @InheritInverseConfiguration
    Stat dataWithoutListToStat(StatDTO statDTO);
    @IterableMapping(qualifiedByName = "StatDataWithoutLists")
    @Named("ListOfBasicStatData")
    List<Stat> mapToStat(List<StatDTO> statDTOList);
    @IterableMapping(qualifiedByName = "StatDTODataWithoutLists")
    @Named("ListOfBasicStatDTOData")
    List<StatDTO> mapToStatDTO(List<Stat> statList);
}
