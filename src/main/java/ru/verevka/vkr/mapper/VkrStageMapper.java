package ru.verevka.vkr.mapper;

import org.mapstruct.Mapper;
import ru.verevka.vkr.domain.VkrStage;
import ru.verevka.vkr.dto.VkrStageDto;

@Mapper(componentModel = "spring")
public interface VkrStageMapper {
    VkrStageDto vkrStageToVkrStageDto(VkrStage vkrStage);
    VkrStage vkrStageDtoToVkrStage(VkrStageDto vkrStageDto);
}
