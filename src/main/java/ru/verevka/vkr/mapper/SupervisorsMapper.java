package ru.verevka.vkr.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.verevka.vkr.domain.Supervisors;
import ru.verevka.vkr.dto.SupervisorsCreateDto;
import ru.verevka.vkr.dto.SupervisorsDto;

@Mapper(componentModel = "spring")
public interface SupervisorsMapper {
    @Mappings({
            @Mapping(target = "vkrTitle", expression = "java(supervisors.getVkr().stream().map(entity -> entity.getTitle()).toList())")})
    SupervisorsDto supervisorsToSupervisorsDto(Supervisors supervisors);
    @Mapping(target = "vkr", ignore = true)
    Supervisors supervisorCreateDtoToSupervisors(SupervisorsCreateDto supervisorsCreateDto);
//    @Mappings({
//            @Mapping(source = "vkrTitle", target = "vkr.title")})
//    Supervisors supervisorsDtoToSupervisors(SupervisorsDto supervisorsDto);

}
