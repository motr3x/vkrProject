package ru.verevka.vkr.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.verevka.vkr.domain.Student;
import ru.verevka.vkr.domain.Vkr;
import ru.verevka.vkr.domain.VkrStage;
import ru.verevka.vkr.dto.StudentDto;
import ru.verevka.vkr.dto.VkrStageDto;
import ru.verevka.vkr.exception.VkrNotFoundException;
import ru.verevka.vkr.exception.VkrStageNotFoundException;
import ru.verevka.vkr.mapper.VkrStageMapper;
import ru.verevka.vkr.repository.VkrStageRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class VkrStageServiceTest {
    private static final Long VKR_ID = 1L;
    private static final Long VKR_STAGE_ID = 1L;
    @InjectMocks
    private VkrStageService vkrStageService;
    @Mock
    private VkrStageRepository vkrStageRepository;
    @Mock
    private VkrStageMapper vkrStageMapper;
    @Mock
    private VkrService vkrService;
    @BeforeEach
    void up(){
        Optional<VkrStage> empty = Optional.empty();
        Mockito.when(vkrStageRepository.findById(VKR_ID)).thenReturn(empty);
    }

    @Test
    void getAllStageOfVkrShouldReturnListOfStudentDto() {

        List<VkrStageDto> vkrStageDtoList = List.of(new VkrStageDto());
        Optional<List<VkrStage>> vkrStagesList = Optional.of(List.of(new VkrStage()));

        when(vkrStageRepository.getAllVkrStageByVkr_Id(VKR_ID)).thenReturn(vkrStagesList);
        when(vkrStageMapper.vkrStageToVkrStageDto(any())).thenReturn(vkrStageDtoList.get(0));

        Assertions.assertIterableEquals(vkrStageDtoList, vkrStageService.getAllByVkrId(VKR_ID));
    }

    @Test
    void removeShouldReturnMessage(){
        Optional<VkrStage> VkrStage = Optional.of(new VkrStage());
        String message = "VkrStage was delete";

        when(vkrStageRepository.findById(VKR_ID)).thenReturn(VkrStage);
        doNothing().when(vkrStageRepository).removeVkrStageByIdAndVkr_Id(VKR_STAGE_ID, VKR_ID);

        Assertions.assertEquals(message, vkrStageService.remove(VKR_ID, VKR_STAGE_ID));
    }

    @Test
    void methodsShouldThrowsException(){
        Assertions.assertThrows(VkrStageNotFoundException.class, () -> vkrStageService.getAllByVkrId(VKR_ID));
        Assertions.assertThrows(VkrStageNotFoundException.class, () -> vkrStageService.getById(VKR_ID,VKR_STAGE_ID));
    }
}