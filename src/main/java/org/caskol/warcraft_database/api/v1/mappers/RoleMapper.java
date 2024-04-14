package org.caskol.warcraft_database.api.v1.mappers;

import org.caskol.warcraft_database.api.v1.dto.IconDTO;
import org.caskol.warcraft_database.api.v1.dto.RoleDTO;
import org.caskol.warcraft_database.api.v1.dto.SpecDTO;
import org.caskol.warcraft_database.api.v1.models.Icon;
import org.caskol.warcraft_database.api.v1.models.Role;
import org.caskol.warcraft_database.api.v1.models.Spec;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper {
    @Named("AllDataToDTO")
    RoleDTO allDataToRoleDTO(Role role);
    @Named("DataWithoutSpecList")
    @Mapping(target = "specList", ignore = true)
    RoleDTO dataWithoutListToRoleDTO(Role role);
    @Mapping(target = "id", ignore = true)
    Role toRole(RoleDTO roleDTO);
    @Mapping(target = "id", ignore = true)
    void updateRoleFromDTO(RoleDTO roleDTO, @MappingTarget Role role);
    //Почему я не использую uses={IconMapper.class}? Потому что код, созданный MapStruct
    //пытается ОБНОВИТЬ элемент iconDTO (вызов метода updateIconFromDTO), а обновление Icon - запрещено в Hibernate/JPA для @OneToOne
    //без использования Cascade ( не хочу чтобы клиент мог обновить данные другого объекта, находясь в одном объекте)
    //Код, который генерируется ниже, создает НОВЫЙ объект Icon и вставляет его через сеттер
    //Поэтому ошибок со стороны hibernate не будет!
    Icon toIcon(IconDTO iconDTO); //этот метод создаст самостоятельно новый объект
    @Mapping(target="role",qualifiedByName = "DataWithoutSpecList")
    List<SpecDTO> mapSpecDTOToEntity(List<Spec> specList);
    @Mapping(target="role", qualifiedByName = "DataWithoutSpecList")
    List<Spec> mapEntityToRoleDTO(List<SpecDTO> specDTOList);
}
