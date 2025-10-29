package ru.verevka.vkr.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VkrDto {
    @NotBlank(message = "Title should be")
    private String title;
    @Min(1)
    @Max(5)
    private Long finalGrade;
    private Double overallProgress;
    @Past()
    private Date lastUpdate = new Date();

}
