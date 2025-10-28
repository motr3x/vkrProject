package ru.verevka.vkr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.verevka.vkr.domain.Vkr;
import ru.verevka.vkr.domain.VkrStage;

import java.util.List;
import java.util.Optional;

public interface VkrStageRepository extends JpaRepository<VkrStage, Long> {
    Optional<List<VkrStage>> getAllVkrStageByVkr_Id(Long vkrId);

    void removeVkrStageByIdAndVkr_Id(Long vkrStageId, Long vkrId);

    Optional<VkrStage> getVkrStageByIdAndVkr_Id(Long stageId, Long vkrId);
}
