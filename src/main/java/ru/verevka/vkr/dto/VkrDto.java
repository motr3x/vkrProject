package ru.verevka.vkr.dto;

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
    private String title;
    private Long finalGrade;
    private Double overallProgress;
    private Date lastUpdate = new Date();

}
