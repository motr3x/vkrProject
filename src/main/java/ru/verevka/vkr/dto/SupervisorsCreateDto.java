package ru.verevka.vkr.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupervisorsCreateDto {
    private String firstName;
    private String secondName;
    private String middleName;
    private String academicDegree;
}

