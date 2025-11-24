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

    public RegistrationUserDto(String firstName, String secondName, String middleName, String email, String password, String confirmPassword, boolean isSupervisor) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        IsSupervisor = isSupervisor;
    }
}
