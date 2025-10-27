package ru.verevka.vkr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.verevka.vkr.domain.Student;
import ru.verevka.vkr.dto.StudentDto;
import ru.verevka.vkr.dto.SupervisorsDto;
import ru.verevka.vkr.service.SupervisorsService;

import java.util.List;

@RestController
@RequestMapping("/api/supervisors")
public class SupervisorsController {
    private final SupervisorsService supervisorsService;

    public SupervisorsController(SupervisorsService supervisorsService) {
        this.supervisorsService = supervisorsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupervisorsDto> getSupervisorById(@PathVariable Long id){
        return ResponseEntity.ok(supervisorsService.getSupervisorsById(id));
    }

    @GetMapping("/allStudents/{id}")
    public ResponseEntity<List<StudentDto>> getAllStudentsByIdSupervisor(@PathVariable Long id){
        return ResponseEntity.ok(supervisorsService.getAllStudent(id));
    }
}
