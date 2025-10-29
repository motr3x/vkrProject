package ru.verevka.vkr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.verevka.vkr.domain.Supervisors;
import ru.verevka.vkr.dto.StudentDto;
import ru.verevka.vkr.dto.SupervisorsCreateDto;
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
    private final StudentMapper studentMapper;
    private final VkrService vkrService;

    public SupervisorsService(SupervisorsRepository supervisorsRepository, SupervisorsMapper supervisorsMapper,StudentMapper studentMapper, VkrService vkrService) {
        this.supervisorsRepository = supervisorsRepository;
        this.supervisorsMapper = supervisorsMapper;
        this.studentMapper = studentMapper;
        this.vkrService = vkrService;
    }
    
    public SupervisorsDto getById(Long id) {
        return supervisorsMapper.supervisorsToSupervisorsDto(supervisorsRepository.findById(id).orElseThrow(() -> new SupervisorNotFoundException("Supervisor with id " + id + " doesn't found")));
    }

    public List<StudentDto> getAllStudent(Long id) {
        return supervisorsRepository.getAllStudentById(id).orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " doesn't found"))
                .stream().map(studentMapper::studentToStudentDto).toList();
    }

    public SupervisorsDto save(SupervisorsCreateDto supervisorsCreateDto) {
        return supervisorsMapper.supervisorsToSupervisorsDto(supervisorsRepository.save(supervisorsMapper.supervisorCreateDtoToSupervisors(supervisorsCreateDto)));
    }

    @Transactional
    public String remove(Long id) {
        supervisorsRepository.removeById(supervisorsRepository.findById(id).orElseThrow(() -> new SupervisorNotFoundException("Supervisor with id " + " doesn't found")).getId());
        return "Supervisor was deleted";
    }

    //todo Add validation to drop many if statement
    @Transactional
    public SupervisorsDto update(Long id, SupervisorsDto supervisorsDto) {
        Supervisors existingSupervisor = supervisorsRepository.findById(id).orElseThrow(() -> new SupervisorNotFoundException("Supervisor with id " + id + " in doesn't found"));
        if(supervisorsDto.getAcademicDegree() != null)
            existingSupervisor.setAcademicDegree(supervisorsDto.getAcademicDegree());
        if(supervisorsDto.getFirstName() != null)
            existingSupervisor.setFirstName(supervisorsDto.getFirstName());
        if(supervisorsDto.getMiddleName() != null)
            existingSupervisor.setMiddleName(supervisorsDto.getMiddleName());
        if(supervisorsDto.getSecondName() != null)
            existingSupervisor.setSecondName(supervisorsDto.getSecondName());
        if(!supervisorsDto.getVkrTitle().isEmpty()){
            existingSupervisor.getVkr().forEach(vkr -> vkr.setSupervisors(null));
            // Set vkr to entity from dto
            existingSupervisor.setVkr(supervisorsDto.getVkrTitle().stream().map(vkrService::getByTitle).toList());
            // Set supervisor id to vkr
            existingSupervisor.getVkr().forEach(vkr -> vkr.setSupervisors(supervisorsRepository.findById(id).get()));
        }
        vkrService.removeIfSupervisorIsNull();

        return supervisorsMapper.supervisorsToSupervisorsDto(existingSupervisor);
    }

    public List<SupervisorsDto> getAll() {
        return supervisorsRepository.findAll().stream().map(supervisorsMapper::supervisorsToSupervisorsDto).toList();
    }
}
