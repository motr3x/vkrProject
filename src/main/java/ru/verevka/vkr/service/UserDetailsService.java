package ru.verevka.vkr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.verevka.vkr.domain.Student;
import ru.verevka.vkr.domain.Supervisors;
import ru.verevka.vkr.dto.RegistrationUserDto;
import ru.verevka.vkr.repository.RoleRepository;
import ru.verevka.vkr.repository.StudentRepository;
import ru.verevka.vkr.repository.SupervisorsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final SupervisorsRepository supervisorsRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    public Optional<Student> findStudentByEmail(String email){
        return studentRepository.findByEmail(email);
    }
    public Optional<Supervisors> findSupervisorByEmail(String email){
        return supervisorsRepository.findByEmail(email);
    }


    //todo need unit test
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        Optional<Student> student = findStudentByEmail(username);
        Optional<Supervisors> supervisors = findSupervisorByEmail(username);
        if(student.isPresent()){
            return new User(
                    student.get().getEmail(),
                    student.get().getPasswordHash(),
                    student.get().getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList())
            );
        }
        supervisors.orElseThrow(()-> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new User(
                supervisors.get().getEmail(),
                supervisors.get().getPasswordHash(),
                supervisors.get().getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList())
        );
    }

    //todo need unit test
    public Supervisors createNewSupervisor(RegistrationUserDto registrationUserDto) {
        Supervisors supervisors = new Supervisors();
        supervisors.setEmail(registrationUserDto.getEmail());
        supervisors.setMiddleName(registrationUserDto.getMiddleName());
        supervisors.setFirstName(registrationUserDto.getFirstName());
        supervisors.setSecondName(registrationUserDto.getSecondName());
        supervisors.setPasswordHash(passwordEncoder.encode(registrationUserDto.getPassword()));
        supervisors.setRoles(List.of(roleRepository.findByName("ROLE_SUPERVISOR").get()));

        return supervisorsRepository.save(supervisors);
    }

    //todo need unit test
    public Student createNewStudent(RegistrationUserDto registrationUserDto){
        Student student = new Student();
        student.setEmail(registrationUserDto.getEmail());
        student.setMiddleName(registrationUserDto.getMiddleName());
        student.setFirstName(registrationUserDto.getFirstName());
        student.setSecondName(registrationUserDto.getSecondName());
        student.setPasswordHash(passwordEncoder.encode(registrationUserDto.getPassword()));
        student.setRoles(List.of(roleRepository.findByName("ROLE_STUDENT").get()));

        return studentRepository.save(student);
    }

}
