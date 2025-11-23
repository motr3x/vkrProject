package ru.verevka.vkr.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegistrationUserDto {
    private String firstName;
    private String secondName;
    private String middleName;
    private String email;
    private String password;
    private String confirmPassword;
    @JsonProperty
    private boolean IsSupervisor;
}
