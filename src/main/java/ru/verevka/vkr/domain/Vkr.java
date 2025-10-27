package ru.verevka.vkr.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
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

    @ManyToOne
    private Supervisors supervisors;

    private String title;
    private Long finalGrade;
    private Double overallProgress;
    private Date lastUpdate = new Date();

    @OneToMany(mappedBy = "vkr", cascade = CascadeType.ALL)
    private List<VkrStage> vkrStages;
}
