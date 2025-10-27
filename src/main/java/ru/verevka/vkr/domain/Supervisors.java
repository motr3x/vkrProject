package ru.verevka.vkr.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Supervisors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String secondName;
    private String middleName;
    private String academicDegree;


    @OneToMany(mappedBy = "supervisors", cascade = CascadeType.ALL)
    private List<Vkr> vkr = new ArrayList<>();

    @Transient
    private List<String> vkrTitle = !this.getVkr().isEmpty() ? this.getVkr().stream().map(Vkr::getTitle).toList() : new ArrayList<>();
}
