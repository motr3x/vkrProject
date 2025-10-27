package ru.verevka.vkr.service;

import org.springframework.stereotype.Service;
import ru.verevka.vkr.domain.Student;
import ru.verevka.vkr.dto.StudentDto;
import ru.verevka.vkr.dto.SupervisorsDto;
import ru.verevka.vkr.exception.StudentNotFoundException;
import ru.verevka.vkr.exception.SupervisorNotFoundException;
import ru.verevka.vkr.mapper.StudentMapper;
import ru.verevka.vkr.mapper.SupervisorsMapper;
import ru.verevka.vkr.repository.SupervisorsRepository;

import java.util.List;

@Service
public class SupervisorsService {
    private final SupervisorsRepository supervisorsRepository;
    private final SupervisorsMapper supervisorsMapper;
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    public SupervisorsService(SupervisorsRepository supervisorsRepository, SupervisorsMapper supervisorsMapper, StudentService studentService, StudentMapper studentMapper) {
        this.supervisorsRepository = supervisorsRepository;
        this.supervisorsMapper = supervisorsMapper;
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }
    
    public SupervisorsDto getSupervisorsById(Long id) {
        return supervisorsMapper.supervisorsToSupervisorsDto(supervisorsRepository.findById(id).orElseThrow(() -> new SupervisorNotFoundException("Supervisor with id " + id + " doesn't found")));
    }

    public List<StudentDto> getAllStudent(Long id) {
        return supervisorsRepository.getAllStudentById(id).orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " doesn't found"))
                .stream().map(studentMapper::studentToStudentDto).toList();
    }
}
