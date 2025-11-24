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
import ru.verevka.vkr.dto.StudentDto;
import ru.verevka.vkr.dto.VkrDto;
import ru.verevka.vkr.exception.StudentNotFoundException;
import ru.verevka.vkr.exception.VkrNotFoundException;
import ru.verevka.vkr.mapper.VkrMapper;
import ru.verevka.vkr.repository.VkrRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
    void removeShouldReturnMessage(){
        Optional<Vkr> vkr = Optional.of(new Vkr());
        String message = "Vkr was deleted";

        when(vkrRepository.findById(ID)).thenReturn(vkr);
        doNothing().when(vkrRepository).removeById(ID);

        Assertions.assertEquals(message, vkrService.remove(ID));
    }

    @Test
    void getAllShouldReturnListOfStudentDto() {
        List<VkrDto> vkrDtoList = List.of(new VkrDto());
        List<Vkr> vkrList = List.of(new Vkr());

        when(vkrRepository.findAll()).thenReturn(vkrList);
        when(vkrMapper.vkrToVkrDto(any())).thenReturn(vkrDtoList.get(0));

        Assertions.assertIterableEquals(vkrDtoList, vkrService.getAll());
    }

    @Test
    void methodsShouldThrowsVkrNotFoundException(){
        Assertions.assertThrows(VkrNotFoundException.class, () -> vkrService.getDtoById(ID));
        Assertions.assertThrows(VkrNotFoundException.class, () -> vkrService.getByTitle("title"));
        Assertions.assertThrows(VkrNotFoundException.class, () -> vkrService.getById(ID));
        Assertions.assertThrows(VkrNotFoundException.class, () -> vkrService.remove(ID));
        Assertions.assertThrows(VkrNotFoundException.class, () -> vkrService.update(ID, null));
    }
}