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
import ru.verevka.vkr.dto.StudentDto;
import ru.verevka.vkr.exception.StudentNotFoundException;
import ru.verevka.vkr.mapper.StudentMapper;
import ru.verevka.vkr.repository.StudentRepository;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class StudentServiceTest {
    private static final Long ID = 1L;
    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentMapper studentMapper;
    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void up(){
        Optional<Student> empty = Optional.empty();
        Mockito.when(studentRepository.findById(ID)).thenReturn(empty);
    }

    @Test
    void getByIdShouldThrowsException(){
        Assertions.assertThrows(StudentNotFoundException.class, () -> studentService.getById(ID));
    }

    @Test
    void removeShouldThrowsException(){
        Assertions.assertThrows(StudentNotFoundException.class, () -> studentService.remove(ID));
    }

    @Test
    void studentUpdateThrowsException(){
        Assertions.assertThrows(StudentNotFoundException.class, () -> studentService.update(ID, null));
    }

    @Test
    void getByIdShouldReturnStudentSuccess(){
        Optional<Student> student = Optional.of(new Student());
        StudentDto studentDto = new StudentDto();
        Mockito.when(studentRepository.findById(ID)).thenReturn(student);
        Mockito.when(studentMapper.studentToStudentDto(student.get())).thenReturn(studentDto);
        Assertions.assertEquals(studentDto, studentService.getById(ID));
    }

    @Test
    void studentShouldBeUpdate(){
        Student existingStudent = new Student(ID, "Test", "Test", "Test",
                "Test", "Test","Test","Test",null, null);

        StudentDto newStudent = new StudentDto("UpdateTest", "UpdateTest",
                "UpdateTest","UpdateTest","UpdateTest",null);

        Student updateStudent = new Student(ID, newStudent.getFirstName(), newStudent.getSecondName(), newStudent.getMiddleName(),
                newStudent.getCharacteristic(), newStudent.getGroupName(), "Test","Test",null, null);


        StudentDto studentDto = new StudentDto(updateStudent.getFirstName(), updateStudent.getSecondName(), updateStudent.getMiddleName(),
                updateStudent.getCharacteristic(), updateStudent.getGroupName(), null);

        Mockito.when(studentMapper.studentToStudentDto(existingStudent)).thenReturn(newStudent);
        Assertions.assertEquals(studentDto.getCharacteristic(), newStudent.getCharacteristic());
        Assertions.assertEquals(studentDto.getFirstName(), newStudent.getFirstName());
        Assertions.assertEquals(studentDto.getGroupName(), newStudent.getGroupName());
        Assertions.assertEquals(studentDto.getMiddleName(), newStudent.getMiddleName());
        Assertions.assertEquals(studentDto.getSecondName(), newStudent.getSecondName());
        Assertions.assertEquals(studentDto.getVkrTitle(), newStudent.getVkrTitle());

    }
}