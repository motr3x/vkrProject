package ru.verevka.vkr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.verevka.vkr.domain.Vkr;
import ru.verevka.vkr.exception.VkrNotFoundException;
import ru.verevka.vkr.service.VkrService;
@RestController
@RequestMapping("/api/vkr")
public class VkrController {
    private final VkrService vkrService;

    public VkrController(VkrService vkrService) {
        this.vkrService = vkrService;
    }

    @GetMapping("")
    public Vkr getVkrById(Long id){
        return vkrService.getVkrById(id).orElseThrow(
                () -> new VkrNotFoundException(("Vkr with id " + id + " doesn't exist")));
    }

}
