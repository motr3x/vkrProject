package ru.verevka.vkr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.verevka.vkr.dto.VkrStageDto;
import ru.verevka.vkr.service.VkrStageService;

import java.util.List;

@RestController
@RequestMapping("/api/vkrStages")
public class VkrStageController {
    private final VkrStageService vkrStageService;

    public VkrStageController(VkrStageService vkrStageService) {
        this.vkrStageService = vkrStageService;
    }

    @GetMapping("/{vkrId}")
    public ResponseEntity<List<VkrStageDto>> getAllVkrStageByVkrId(@PathVariable Long vkrId){
        return ResponseEntity.ok(vkrStageService.getAllVkrStageByVkrId(vkrId));
    }

    @GetMapping("/{vkrId}/stage/{stageId}")
    public ResponseEntity<VkrStageDto> getVkrStageById(@PathVariable Long vkrId,
                                                       @PathVariable Long stageId){
        return ResponseEntity.ok(vkrStageService.getVkrStageById(vkrId, stageId));
    }

    @PostMapping("/{vkrId}")
    public ResponseEntity<VkrStageDto> createVkrStage(@PathVariable Long vkrId,
                                                      @RequestBody VkrStageDto vkrStageDto){
        return ResponseEntity.ok(vkrStageService.saveVkrStageByVkr_Id(vkrId, vkrStageDto));
    }

    @PutMapping("/{vkrId}/stage/{stageId}")
    public ResponseEntity<VkrStageDto> updateVkrStage(@PathVariable Long vkrId,
                                                      @PathVariable Long stageId,
                                                      @RequestBody VkrStageDto vkrStageDto){
        return ResponseEntity.ok(vkrStageService.updateVkrStage(vkrId, stageId, vkrStageDto));
    }

    @DeleteMapping("/{vkrId}/stage/{stageId}")
    public ResponseEntity<String> deleteVkrStage(@PathVariable Long vkrId,
                                                 @PathVariable Long stageId){
        return ResponseEntity.ok(vkrStageService.removeById(vkrId, stageId));
    }
}
