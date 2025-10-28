package ru.verevka.vkr.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.verevka.vkr.domain.Status;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VkrStageDto {
    private String title;
    private Date plannedDeadline;
    private Status status;
    private Double stageProgress;
    private String description;
    private String supervisorFeedback;
}
