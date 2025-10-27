package ru.verevka.vkr.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupervisorsDto {
    private String firstName;
    private String secondName;
    private String middleName;
    private String academicDegree;
    private List<String> vkrTitle;
}
