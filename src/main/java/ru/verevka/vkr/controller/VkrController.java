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
import ru.verevka.vkr.domain.Vkr;
import ru.verevka.vkr.dto.VkrDto;
import ru.verevka.vkr.service.VkrService;

import java.util.List;

@RestController
@RequestMapping("/api/vkr")
public class VkrController {

    private final VkrService vkrService;

    public VkrController(VkrService vkrService) {
        this.vkrService = vkrService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VkrDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(vkrService.getDtoById(id));
    }

    @GetMapping()
    public ResponseEntity<List<VkrDto>> getAll(){
        return ResponseEntity.ok(vkrService.getAll());
    }

    @GetMapping("/{/supervisorId}")
    public ResponseEntity<List<VkrDto>> getAllBySupervisorId(@PathVariable Long supervisorId){
        return ResponseEntity.ok(vkrService.getAllBySupervisorId(supervisorId));
    }

    @PostMapping()
    public ResponseEntity<VkrDto> create(@Valid @RequestBody VkrDto vkrDto){
        return ResponseEntity.ok(vkrService.save(vkrDto));
    }

    @PutMapping("/{vkrId}")
    public ResponseEntity<VkrDto> update(@PathVariable Long vkrId, @Valid @RequestBody VkrDto vkrDto){
        return ResponseEntity.ok(vkrService.update(vkrId, vkrDto));
    }

    @DeleteMapping("/{vkrId}")
    public ResponseEntity<String> remove(@PathVariable Long vkrId){
        return ResponseEntity.ok(vkrService.remove(vkrId));
    }
}
