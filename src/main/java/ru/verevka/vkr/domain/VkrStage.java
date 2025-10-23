package ru.verevka.vkr.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VkrStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vkr vkr;

    @ManyToOne
    private StageTemplate stageTemplate;

    private Date plannedDeadline;
    private Date actualDeadline;
    private Status status;
    private Double stageProgress;
    private String comments;
    private String supervisorFeedback;


}
