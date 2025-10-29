package ru.verevka.vkr.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VkrStageDto {
    @NotBlank(message = "Title should be")
    private String title;
    @NotBlank
    private Date plannedDeadline;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Double stageProgress;
    private String description;
    private String supervisorFeedback;
}

