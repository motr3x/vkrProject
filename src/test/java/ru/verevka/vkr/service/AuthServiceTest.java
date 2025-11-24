package ru.verevka.vkr.service;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import ru.verevka.vkr.configuration.security.jwtConfig.JwtTokenUtils;
import ru.verevka.vkr.domain.Student;
import ru.verevka.vkr.domain.Supervisors;
import ru.verevka.vkr.dto.JwtRequest;
import ru.verevka.vkr.dto.RegistrationUserDto;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthServiceTest {
    @InjectMocks
    private AuthService authService;
    @Mock
    private UserDetailsService userService;
    @Mock
    private JwtTokenUtils jwtTokenUtils;
    @Mock
    private AuthenticationManager authenticationManager;

    private final RegistrationUserDto registrationStudentDto = new RegistrationUserDto("test","test",
            "test","test","password","password", false);
    private final RegistrationUserDto registrationSupervisorDto = new RegistrationUserDto("test","test",
            "test","test","password","password", true);
    @Test
    void createNewUserShouldThrowsBadRequestExceptionIfPasswordsDontEquals() {
        RegistrationUserDto registrationInvalidDto = new RegistrationUserDto("test","test",
            "test","test","password","invalidPassword", false);
        assertEquals(HttpStatus.BAD_REQUEST, authService.createNewUser(registrationInvalidDto).getStatusCode());
    }

    @Test
    void createNewStudentShouldBeSuccess() {
        Optional<Student> emptyStudent = Optional.empty();
        Optional<Supervisors> emptySupervisor = Optional.empty();
        when(userService.findStudentByEmail(registrationStudentDto.getEmail())).thenReturn(emptyStudent);
        when(userService.findSupervisorByEmail(registrationStudentDto.getEmail())).thenReturn(emptySupervisor);
        when(userService.createNewStudent(registrationStudentDto)).thenReturn(new Student());
        assertEquals(HttpStatus.OK, authService.createNewUser(registrationStudentDto).getStatusCode());
    }
    @Test
    void createNewSupervisorShouldBeSuccess() {
        Optional<Student> emptyStudent = Optional.empty();
        Optional<Supervisors> emptySupervisor = Optional.empty();
        when(userService.findStudentByEmail(registrationSupervisorDto.getEmail())).thenReturn(emptyStudent);
        when(userService.findSupervisorByEmail(registrationSupervisorDto.getEmail())).thenReturn(emptySupervisor);
        when(userService.createNewSupervisor(registrationSupervisorDto)).thenReturn(new Supervisors());
        assertEquals(HttpStatus.OK, authService.createNewUser(registrationSupervisorDto).getStatusCode());
    }

    @Test
    void createNewUserShouldThrowsBadRequestExceptionIfStudentExist() {
        Optional<Student> existStudent = Optional.of(new Student());
        Optional<Supervisors> emptySupervisor = Optional.empty();
        when(userService.findStudentByEmail(registrationStudentDto.getEmail())).thenReturn(existStudent);
        when(userService.findSupervisorByEmail(registrationStudentDto.getEmail())).thenReturn(emptySupervisor);
        assertEquals(HttpStatus.BAD_REQUEST, authService.createNewUser(registrationStudentDto).getStatusCode());
    }

    @Test
    void createNewUserShouldThrowsBadRequestExceptionIfSupervisorExist() {
        Optional<Student> emptyStudent = Optional.empty();
        Optional<Supervisors> existSupervisor = Optional.of(new Supervisors());
        when(userService.findStudentByEmail(registrationStudentDto.getEmail())).thenReturn(emptyStudent);
        when(userService.findSupervisorByEmail(registrationStudentDto.getEmail())).thenReturn(existSupervisor);
        assertEquals(HttpStatus.BAD_REQUEST, authService.createNewUser(registrationStudentDto).getStatusCode());
    }

    @Test
    void createAuthTokenShouldBeSuccess() {
        UserDetails userDetails = null;
        JwtRequest authRequest = new JwtRequest("Username", "Password");
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()))).thenReturn(null);
        when(userService.loadUserByUsername(authRequest.getUsername())).thenReturn(userDetails);
        when(jwtTokenUtils.generateToken(userDetails)).thenReturn("token");
        assertEquals(HttpStatus.OK, authService.createAuthToken(authRequest).getStatusCode());
    }
    @Test
    void createAuthTokenShouldThrowsBadCredentialsException() {
        JwtRequest authRequest = new JwtRequest("invalidUsername", "invalidPassword");
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()))).thenThrow(BadCredentialsException.class);
        assertEquals(HttpStatus.UNAUTHORIZED,  authService.createAuthToken(authRequest).getStatusCode());
    }

}