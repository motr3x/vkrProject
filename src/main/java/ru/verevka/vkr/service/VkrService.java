package ru.verevka.vkr.service;

import org.springframework.stereotype.Service;
import ru.verevka.vkr.domain.Vkr;
import ru.verevka.vkr.repository.VkrRepository;

import java.util.Optional;

@Service
public class VkrService {
    private final VkrRepository vkrRepository;

    public VkrService(VkrRepository vkrRepository) {
        this.vkrRepository = vkrRepository;
    }

    public Optional<Vkr> getVkrById(Long id){
        return vkrRepository.findById(id);
    }

}
