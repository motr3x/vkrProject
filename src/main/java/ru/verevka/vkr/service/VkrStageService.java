package ru.verevka.vkr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.verevka.vkr.domain.Vkr;
import ru.verevka.vkr.domain.VkrStage;
import ru.verevka.vkr.dto.VkrStageDto;
import ru.verevka.vkr.exception.VkrStageNotFoundException;
import ru.verevka.vkr.mapper.VkrStageMapper;
import ru.verevka.vkr.repository.VkrStageRepository;

import java.util.List;

@Service
public class VkrStageService {
    private final VkrStageRepository vkrStageRepository;
    private final VkrStageMapper vkrStageMapper;
    private final VkrService vkrService;

    public VkrStageService(VkrStageRepository vkrStageRepository, VkrStageMapper vkrStageMapper, VkrService vkrService) {
        this.vkrStageRepository = vkrStageRepository;
        this.vkrStageMapper = vkrStageMapper;
        this.vkrService = vkrService;
    }

    public List<VkrStageDto> getAllByVkrId(Long id){
        return vkrStageRepository.getAllVkrStageByVkr_Id(id).orElseThrow(() -> new VkrStageNotFoundException("Vkr stage with id " + id + " doesn't found"))
                .stream().map(vkrStageMapper::vkrStageToVkrStageDto).toList();
    }
    //todo !!!!!
    @Transactional
    public VkrStageDto saveByVkr_Id(Long id, VkrStageDto vkrStageDto) {
        vkrService.getById(id);
        VkrStage vkrStage = vkrStageMapper.vkrStageDtoToVkrStage(vkrStageDto);
        vkrStage.setVkr(vkrService.getById(id));
        return vkrStageMapper.vkrStageToVkrStageDto(vkrStageRepository.save(vkrStage));
    }

    //todo Add validation to drop many if statement
    @Transactional
    public VkrStageDto update(Long vkrId, Long stageId, VkrStageDto vkrStageDto) {
        Vkr vkr = vkrService.getById(vkrId);
        VkrStage vkrStage = vkr.getVkrStages().stream().filter(entity -> entity.getId().equals(stageId)).toList().get(0
        );
        if(vkrStageDto.getTitle() != null)
            vkrStage.setTitle(vkrStageDto.getTitle());
        if(vkrStageDto.getDescription() != null)
            vkrStage.setDescription(vkrStageDto.getDescription());
        if(vkrStageDto.getSupervisorFeedback() != null)
            vkrStage.setSupervisorFeedback(vkrStageDto.getSupervisorFeedback());
        if(vkrStageDto.getStageProgress() != null)
            vkrStage.setStageProgress(vkrStageDto.getStageProgress());
        if(vkrStageDto.getPlannedDeadline() != null)
            vkrStage.setPlannedDeadline(vkrStageDto.getPlannedDeadline());
        if(vkrStageDto.getStatus() != null)
            vkrStage.setStatus(vkrStageDto.getStatus());
        return vkrStageMapper.vkrStageToVkrStageDto(vkrStageRepository.save(vkrStage));
    }

    @Transactional
    public String remove(Long vkrId, Long stageId) {
        vkrStageRepository.removeVkrStageByIdAndVkr_Id(stageId, vkrId);
        return "VkrStage was delete";
    }

    public VkrStageDto getById(Long vkrId, Long stageId) {
        return vkrStageMapper.vkrStageToVkrStageDto(vkrStageRepository.getVkrStageByIdAndVkr_Id(stageId, vkrId).orElseThrow(() -> new VkrStageNotFoundException("VkrStage with id " + stageId + " doesn't found")));
    }
}
