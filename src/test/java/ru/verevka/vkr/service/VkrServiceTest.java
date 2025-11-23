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
import ru.verevka.vkr.exception.StudentNotFoundException;
import ru.verevka.vkr.exception.VkrNotFoundException;
import ru.verevka.vkr.mapper.VkrMapper;
import ru.verevka.vkr.repository.VkrRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class VkrServiceTest {
    private static final Long ID = 1L;
    @InjectMocks
    private VkrService vkrService;
    @Mock
    private VkrRepository vkrRepository;
    @Mock
    private VkrMapper vkrMapper;

    @BeforeEach
    void up(){
        Optional<Vkr> empty = Optional.empty();
        Mockito.when(vkrRepository.findById(ID)).thenReturn(empty);
    }

    @Test
    void getByIdShouldThrowsException(){
        Assertions.assertThrows(VkrNotFoundException.class, () -> vkrService.getById(ID));
    }

    @Test
    void removeShouldThrowsException(){
        Assertions.assertThrows(VkrNotFoundException.class, () -> vkrService.remove(ID));
    }

    @Test
    void vkrUpdateThrowsException(){
        Assertions.assertThrows(VkrNotFoundException.class, () -> vkrService.update(ID, null));
    }
}