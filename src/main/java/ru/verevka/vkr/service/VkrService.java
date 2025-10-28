package ru.verevka.vkr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.verevka.vkr.domain.Vkr;
import ru.verevka.vkr.exception.VkrNotFoundException;
import ru.verevka.vkr.repository.VkrRepository;


@Service
public class VkrService {
    private final VkrRepository vkrRepository;

    public VkrService(VkrRepository vkrRepository) {
        this.vkrRepository = vkrRepository;
    }

    public Vkr getVkrByTitle(String title){
        return vkrRepository.findByTitle(title).orElseThrow();
    }


    public Vkr getVkrById(Long id){
        return vkrRepository.findById(id).orElseThrow(()->new VkrNotFoundException("Vkr with this id " + id + " doesn't found"));
    }

    @Transactional
    public void removeIfSupervisorIsNull() {
        vkrRepository.removeIfSupervisorIsNull();
    }
}
