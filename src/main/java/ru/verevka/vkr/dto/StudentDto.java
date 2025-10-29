package ru.verevka.vkr.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    @NotBlank(message = "FirstName should be!")
    private String firstName;
    @NotBlank(message = "SecondName should be!")
    private String secondName;
    private String middleName;
    private String characteristic;
    @NotBlank(message = "GroupName should be!")
    private String groupName;
    private String vkrTitle;
}
