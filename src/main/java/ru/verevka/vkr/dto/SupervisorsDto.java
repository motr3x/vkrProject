package ru.verevka.vkr.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.verevka.vkr.domain.Vkr;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupervisorsDto {
    @NotBlank(message = "FirstName should be!")
    private String firstName;
    @NotBlank(message = "SecondName should be!")
    private String secondName;
    private String middleName;
    private String academicDegree;
    private List<String> vkrTitle;
}

