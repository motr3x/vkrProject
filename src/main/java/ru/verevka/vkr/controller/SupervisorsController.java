package ru.verevka.vkr.controller;

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
import ru.verevka.vkr.dto.SupervisorsCreateDto;
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
    public ResponseEntity<SupervisorsDto> get(@PathVariable Long id){
        return ResponseEntity.ok(supervisorsService.getById(id));
    }

    @GetMapping("/allStudents/{id}")
    public ResponseEntity<List<StudentDto>> getAllStudentsOwnSupervisor(@PathVariable Long id){
        return ResponseEntity.ok(supervisorsService.getAllStudent(id));
    }

    @PostMapping()
    public ResponseEntity<SupervisorsDto> create(@RequestBody SupervisorsCreateDto supervisorsDto){
        return ResponseEntity.ok(supervisorsService.save(supervisorsDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(supervisorsService.remove(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupervisorsDto> update(@PathVariable Long id,
                                                           @RequestBody SupervisorsDto supervisorsDto){
        return ResponseEntity.ok(supervisorsService.update(id, supervisorsDto));
    }
}
