package ru.verevka.vkr.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupervisorsCreateDto {
    @NotBlank(message = "FirstName should be!")
    private String firstName;
    @NotBlank(message = "SecondName should be!")
    private String secondName;
    private String middleName;
    private String academicDegree;
}

