package ru.verevka.vkr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.verevka.vkr.domain.Student;
import ru.verevka.vkr.domain.Vkr;
import ru.verevka.vkr.dto.StudentDto;
import ru.verevka.vkr.exception.StudentNotFoundException;
import ru.verevka.vkr.mapper.StudentMapper;
import ru.verevka.vkr.repository.StudentRepository;
import ru.verevka.vkr.repository.VkrRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final VkrRepository vkrRepository;
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, VkrRepository vkrRepository) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.vkrRepository = vkrRepository;
    }

    public StudentDto getStudentById(Long id){
        return studentMapper.studentToStudentDto(studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " doesn't found.")));
    }

    public List<StudentDto> getAllStudents(){
        return  studentRepository.findAll().stream().map(studentMapper::studentToStudentDto).toList();
    }

    @Transactional
    public String removeStudentById(Long id){
        studentRepository.removeById(studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " in doesn't found")).getId());
        return "Student was deleted";
    }
    @Transactional
    public StudentDto saveStudent(StudentDto student){
        Vkr newVkr = new Vkr();
        newVkr.setTitle(student.getVkrTitle());
        return studentMapper.studentToStudentDto(studentRepository.save(studentMapper.studentDtoToStudent(student)));
    }

    @Transactional
    public StudentDto updateStudentById(Long id, StudentDto studentDto) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " in doesn't found"));
        if(studentDto.getFirstName() != null)
            existingStudent.setFirstName(studentDto.getFirstName());
        if(studentDto.getSecondName() != null)
            existingStudent.setSecondName(studentDto.getSecondName());
        if(studentDto.getMiddleName() != null)
            existingStudent.setMiddleName(studentDto.getMiddleName());
        if(studentDto.getCharacteristic() != null)
            existingStudent.setCharacteristic(studentDto.getCharacteristic());
        if(studentDto.getGroupName() != null)
            existingStudent.setGroupName(studentDto.getGroupName());
        if(studentDto.getVkrTitle() != null)
            existingStudent.getVkr().setTitle(studentDto.getVkrTitle());
        return studentMapper.studentToStudentDto(existingStudent);
    }
}
