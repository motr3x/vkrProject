package ru.verevka.vkr.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.verevka.vkr.domain.Role;
import ru.verevka.vkr.domain.Student;
import ru.verevka.vkr.domain.Supervisors;
import ru.verevka.vkr.repository.RoleRepository;
import ru.verevka.vkr.repository.StudentRepository;
import ru.verevka.vkr.repository.SupervisorsRepository;

import javax.swing.text.html.Option;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserDetailsServiceTest {
    @InjectMocks
    private UserDetailsService userDetailsService;
    @Mock
    private SupervisorsRepository supervisorsRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleRepository roleRepository;


    @Test
    void loadUserByUsernameShouldThrowsUsernameNotFoundException(){
        Optional<Student> student = Optional.empty();
        Optional<Supervisors> supervisors = Optional.empty();

        when(studentRepository.findByEmail(anyString())).thenReturn(student);
        when(supervisorsRepository.findByEmail(anyString())).thenReturn(supervisors);

        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("Space"));
    }

    @Test
    void loadUserByUsernameIfStudentExist() {
        Optional<Supervisors> supervisors = Optional.empty();
        Optional<Student> student = Optional.of(new Student());
        student.get().setEmail("student@mail.ru");
        student.get().setPasswordHash("pass");
        Role role = new Role(1L, "USER");
        Collection<Role> roles = new ArrayList<>();
        roles.add(role);
        student.get().setRoles(roles);

        UserDetails expectedUser = new User(
                student.get().getEmail(),
                student.get().getPasswordHash(),
                student.get().getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList())
        );

        when(studentRepository.findByEmail(anyString())).thenReturn(student);
        when(supervisorsRepository.findByEmail(anyString())).thenReturn(supervisors);

        assertEquals(expectedUser.getUsername(), userDetailsService.loadUserByUsername(student.get().getEmail()).getUsername());
    }

    @Test
    void loadUserByUsernameIfSupervisorExist() {
        Optional<Student> student = Optional.empty();
        Optional<Supervisors> supervisors = Optional.of(new Supervisors());
        supervisors.get().setEmail("supervisor@mail.ru");
        supervisors.get().setPasswordHash("pass");
        Role role = new Role(1L, "SUPERVISOR");
        Collection<Role> roles = new ArrayList<>();
        roles.add(role);
        supervisors.get().setRoles(roles);

        UserDetails expectedUser = new User(
                supervisors.get().getEmail(),
                supervisors.get().getPasswordHash(),
                supervisors.get().getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList())
        );

        when(studentRepository.findByEmail(anyString())).thenReturn(student);
        when(supervisorsRepository.findByEmail(anyString())).thenReturn(supervisors);

        assertEquals(expectedUser.getUsername(), userDetailsService.loadUserByUsername(supervisors.get().getEmail()).getUsername());
    }
}