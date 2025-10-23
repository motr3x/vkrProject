package ru.verevka.vkr.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String firstName;
    private String secondName;
    private String middleName;
    private String characteristic;
    private String groupName;
    private String vkrTitle;
    private String initialsSupervisors;
}
