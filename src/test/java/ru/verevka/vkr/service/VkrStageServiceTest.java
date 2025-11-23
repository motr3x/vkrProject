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
import ru.verevka.vkr.domain.Vkr;
import ru.verevka.vkr.domain.VkrStage;
import ru.verevka.vkr.exception.VkrNotFoundException;
import ru.verevka.vkr.exception.VkrStageNotFoundException;
import ru.verevka.vkr.mapper.VkrStageMapper;
import ru.verevka.vkr.repository.VkrStageRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    void getByIdShouldThrowsException(){
        Assertions.assertThrows(VkrStageNotFoundException.class, () -> vkrStageService.getAllByVkrId(VKR_ID));
    }

    @Test
    void vkrStageUpdateThrowsException(){
        Assertions.assertThrows(VkrStageNotFoundException.class, () -> vkrStageService.getById(VKR_ID,VKR_STAGE_ID));
    }
}