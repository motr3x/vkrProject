package ru.verevka.vkr.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.verevka.vkr.configuration.security.jwtConfig.JwtTokenUtils;
import ru.verevka.vkr.domain.Student;
import ru.verevka.vkr.domain.Supervisors;
import ru.verevka.vkr.dto.JwtRequest;
import ru.verevka.vkr.dto.JwtResponse;
import ru.verevka.vkr.dto.RegistrationUserDto;
import ru.verevka.vkr.dto.UserDto;
import ru.verevka.vkr.exception.AppError;
@Service
public class AuthService {
    private final UserDetailsService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserDetailsService userService, JwtTokenUtils jwtTokenUtils, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtTokenUtils = jwtTokenUtils;
        this.authenticationManager = authenticationManager;
    }

    //todo need unit test
    public ResponseEntity<?> createAuthToken(JwtRequest authRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        }
        catch(BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Invalid login or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    //todo need unit test
    public ResponseEntity<?> createNewUser(RegistrationUserDto registrationUserDto){
        if(!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword()))
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Password not equals"), HttpStatus.BAD_REQUEST);

        if(userService.findStudentByEmail(registrationUserDto.getEmail()).isPresent() || userService.findSupervisorByEmail(registrationUserDto.getEmail()).isPresent())
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "User exists"), HttpStatus.BAD_REQUEST);
        if(registrationUserDto.isIsSupervisor()) {
            Supervisors supervisors = userService.createNewSupervisor(registrationUserDto);
            return ResponseEntity.ok(new UserDto(supervisors.getId(), supervisors.getEmail()));
        }
        Student student = userService.createNewStudent(registrationUserDto);
        return ResponseEntity.ok(new UserDto(student.getId(), student.getEmail()));
    }
}
