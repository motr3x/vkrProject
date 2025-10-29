package ru.verevka.vkr.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.verevka.vkr.dto.StudentDto;
import ru.verevka.vkr.service.StudentService;

import java.util.List;


@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(studentService.getById(id));
    }

    @GetMapping()
    public ResponseEntity<List<StudentDto>> getAll(){
        return ResponseEntity.ok(studentService.getAll());
    }

    @PostMapping()
    public ResponseEntity<StudentDto> add(@Valid @RequestBody StudentDto student){
        return ResponseEntity.ok(studentService.save(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeById(@PathVariable("id") Long id){
        return ResponseEntity.ok(studentService.remove(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateById(@Valid @RequestBody StudentDto student,
                                                        @PathVariable("id") Long id){
        return ResponseEntity.ok(studentService.update(id, student));
    }
}
