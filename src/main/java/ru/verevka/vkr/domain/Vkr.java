package ru.verevka.vkr.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Vkr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "vkr")
    private Student student;

    @OneToOne(mappedBy = "vkr")
    private Supervisors supervisors;

    private String title;
    private Long finalGrade;
    private Double overallProgress;

    @OneToMany(mappedBy = "vkr", cascade = CascadeType.ALL)
    private List<VkrStage> vkrStages;
}
