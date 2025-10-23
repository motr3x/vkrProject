package ru.verevka.vkr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.verevka.vkr.domain.Student;
import ru.verevka.vkr.dto.StudentDto;
import ru.verevka.vkr.exception.StudentNotFoundException;
import ru.verevka.vkr.mapper.StudentMapper;
import ru.verevka.vkr.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentDto getStudentById(Long id){
        Optional<Student> student = studentRepository.findById(id);
        return studentMapper.studentToStudentDto(student.orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " doesn't found.")));
    }

    public List<StudentDto> getAllStudents(){
        return  studentRepository.findAll().stream().map(studentMapper::studentToStudentDto).toList();
    }

    @Transactional
    public void removeStudentById(Long id){
        try {
            studentRepository.findById(id);
        } catch (StudentNotFoundException ex) {
            throw new StudentNotFoundException("Student with id " + id + " doesn't found.");
        }
        studentRepository.removeById(id);
    }
    @Transactional
    public Student saveStudent(StudentDto student){
        return studentRepository.save(studentMapper.studentDtoToStudent(student));
    }
}
