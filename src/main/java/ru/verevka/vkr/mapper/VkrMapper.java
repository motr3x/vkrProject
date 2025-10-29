package ru.verevka.vkr.mapper;

import org.mapstruct.Mapper;
import ru.verevka.vkr.domain.Vkr;
import ru.verevka.vkr.dto.VkrDto;

@Mapper(componentModel = "spring")
public interface VkrMapper {
    Vkr VkrDtoToVkr(VkrDto vkrDto);
    VkrDto vkrToVkrDto(Vkr vkr);
}




