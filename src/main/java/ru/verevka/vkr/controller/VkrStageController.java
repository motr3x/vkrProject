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
    public ResponseEntity<List<VkrStageDto>> getAllByVkrId(@PathVariable Long vkrId){
        return ResponseEntity.ok(vkrStageService.getAllByVkrId(vkrId));
    }

    @GetMapping("/{vkrId}/stage/{stageId}")
    public ResponseEntity<VkrStageDto> getById(@PathVariable Long vkrId,
                                                       @PathVariable Long stageId){
        return ResponseEntity.ok(vkrStageService.getById(vkrId, stageId));
    }

    @PostMapping("/{vkrId}")
    public ResponseEntity<VkrStageDto> create(@PathVariable Long vkrId,
                                              @Valid @RequestBody VkrStageDto vkrStageDto){
        return ResponseEntity.ok(vkrStageService.saveByVkr_Id(vkrId, vkrStageDto));
    }

    @PutMapping("/{vkrId}/stage/{stageId}")
    public ResponseEntity<VkrStageDto> update(@PathVariable Long vkrId,
                                                      @PathVariable Long stageId,
                                              @Valid @RequestBody VkrStageDto vkrStageDto){
        return ResponseEntity.ok(vkrStageService.update(vkrId, stageId, vkrStageDto));
    }

    @DeleteMapping("/{vkrId}/stage/{stageId}")
    public ResponseEntity<String> delete(@PathVariable Long vkrId,
                                                 @PathVariable Long stageId){
        return ResponseEntity.ok(vkrStageService.remove(vkrId, stageId));
    }
}
