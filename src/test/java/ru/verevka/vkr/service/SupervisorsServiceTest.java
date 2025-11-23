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
import ru.verevka.vkr.domain.Supervisors;
import ru.verevka.vkr.dto.StudentDto;
import ru.verevka.vkr.dto.SupervisorsDto;
import ru.verevka.vkr.exception.StudentNotFoundException;
import ru.verevka.vkr.exception.SupervisorNotFoundException;
import ru.verevka.vkr.mapper.StudentMapper;
import ru.verevka.vkr.mapper.SupervisorsMapper;
import ru.verevka.vkr.repository.SupervisorsRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SupervisorsServiceTest {
    private static final Long ID = 1L;
    @InjectMocks
    private SupervisorsService supervisorsService;
    @Mock
    private SupervisorsRepository supervisorsRepository;
    @Mock
    private SupervisorsMapper supervisorsMapper;
    @Mock
    private StudentMapper studentMapper;
    @Mock
    private VkrService vkrService;


    @BeforeEach
    void up(){
        Optional<Supervisors> empty = Optional.empty();
        Mockito.when(supervisorsRepository.findById(ID)).thenReturn(empty);
    }

    @Test
    void getByIdShouldThrowsException(){
        Assertions.assertThrows(SupervisorNotFoundException.class, () -> supervisorsService.getById(ID));
    }

    @Test
    void removeShouldThrowsException(){
        Assertions.assertThrows(SupervisorNotFoundException.class, () -> supervisorsService.remove(ID));
    }

    @Test
    void supervisorsUpdateThrowsException(){
        Assertions.assertThrows(SupervisorNotFoundException.class, () -> supervisorsService.update(ID, null));
    }


}