package ru.verevka.vkr.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.verevka.vkr.dto.Status;

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

    private String title;
    private Date plannedDeadline;
    @Enumerated(EnumType.STRING)
    private Status status = Status.NOT_STARTED;;
    private Double stageProgress = 0.0;
    private String description;
    private String supervisorFeedback;


}
