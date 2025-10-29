package ru.verevka.vkr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.verevka.vkr.domain.Vkr;
import ru.verevka.vkr.dto.VkrDto;
import ru.verevka.vkr.exception.VkrNotFoundException;
import ru.verevka.vkr.mapper.VkrMapper;
import ru.verevka.vkr.repository.VkrRepository;

import java.util.List;


@Service
public class VkrService {
    private final VkrRepository vkrRepository;
    private final VkrMapper vkrMapper;

    public VkrService(VkrRepository vkrRepository, VkrMapper vkrMapper) {
        this.vkrRepository = vkrRepository;
        this.vkrMapper = vkrMapper;
    }

    public Vkr getByTitle(String title){
        return vkrRepository.findByTitle(title).orElseThrow();
    }


    public Vkr getById(Long id){
        return vkrRepository.findById(id).orElseThrow(()->new VkrNotFoundException("Vkr with this id " + id + " doesn't found"));
    }
    public VkrDto getDtoById(Long id){
        return vkrMapper.vkrToVkrDto(vkrRepository.findById(id).orElseThrow(()->new VkrNotFoundException("Vkr with this id " + id + " doesn't found")));
    }

    @Transactional
    public void removeIfSupervisorIsNull() {
        vkrRepository.removeIfSupervisorIsNull();
    }

    public List<VkrDto> getAll() {
        return vkrRepository.findAll().stream().map(vkrMapper::vkrToVkrDto).toList();
    }

    public List<VkrDto> getAllBySupervisorId(Long supervisorId) {
        return vkrRepository.getAllVkrBySupervisors_Id(supervisorId).stream().map(vkrMapper::vkrToVkrDto).toList();
    }

    @Transactional
    public VkrDto save(VkrDto vkrDto) {
        return vkrMapper.vkrToVkrDto(vkrRepository.save(vkrMapper.VkrDtoToVkr(vkrDto)));
    }

    @Transactional
    public VkrDto update(Long vkrId, VkrDto vkrDto) {
        Vkr vkr = getById(vkrId);
        if(vkrDto.getFinalGrade() != null)
            vkr.setFinalGrade(vkrDto.getFinalGrade());
        if(vkrDto.getLastUpdate() != null)
            vkr.setLastUpdate(vkrDto.getLastUpdate());
        if(vkrDto.getOverallProgress() != null)
            vkr.setOverallProgress(vkrDto.getOverallProgress());
        if(vkrDto.getTitle() != null)
            vkr.setTitle((vkrDto.getTitle()));
        return vkrMapper.vkrToVkrDto(vkr);
    }

    public String remove(Long vkrId) {
        vkrRepository.removeById(getById(vkrId).getId());
        return "Vkr was deleted";
    }
}
