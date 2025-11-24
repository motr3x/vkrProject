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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
    void removeShouldReturnMessage(){
        Optional<Supervisors> supervisors = Optional.of(new Supervisors());
        String message = "Supervisor was deleted";

        when(supervisorsRepository.findById(ID)).thenReturn(supervisors);
        doNothing().when(supervisorsRepository).removeById(ID);

        Assertions.assertEquals(message, supervisorsService.remove(ID));
    }

    @Test
    void getAllShouldReturnListOfSupervisorDto() {
        List<SupervisorsDto> supervisorsDtoList = List.of(new SupervisorsDto());
        List<Supervisors> supervisorsList = List.of(new Supervisors());

        when(supervisorsRepository.findAll()).thenReturn(supervisorsList);
        when(supervisorsMapper.supervisorsToSupervisorsDto(any())).thenReturn(supervisorsDtoList.get(0));

        Assertions.assertIterableEquals(supervisorsDtoList, supervisorsService.getAll());
    }

    @Test
    void getAllStudentShouldReturnListOfStudentDto() {
        List<StudentDto> studentDtoList = List.of(new StudentDto());
        Optional<List<Student>> studentList = Optional.of(List.of(new Student()));

        when(supervisorsRepository.getAllStudentById(ID)).thenReturn(studentList);
        when(studentMapper.studentToStudentDto(any())).thenReturn(studentDtoList.get(0));

        Assertions.assertIterableEquals(studentDtoList, supervisorsService.getAllStudent(ID));
    }

    @Test
    void methodsShouldThrowsSupervisorNotFoundException(){
        Assertions.assertThrows(SupervisorNotFoundException.class, () -> supervisorsService.getById(ID));
        Assertions.assertThrows(SupervisorNotFoundException.class, () -> supervisorsService.getById(ID));
        Assertions.assertThrows(StudentNotFoundException.class, () -> supervisorsService.getAllStudent(ID));
        Assertions.assertThrows(SupervisorNotFoundException.class, () -> supervisorsService.remove(ID));
        Assertions.assertThrows(SupervisorNotFoundException.class, () -> supervisorsService.update(ID, null));
    }
}