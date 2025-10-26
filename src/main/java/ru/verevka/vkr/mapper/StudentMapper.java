package ru.verevka.vkr.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.verevka.vkr.domain.Student;
import ru.verevka.vkr.dto.StudentDto;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mappings({
            @Mapping(source = "vkr.title", target = "vkrTitle")})
    StudentDto studentToStudentDto(Student student);
    @Mappings({
            @Mapping(source = "vkrTitle", target = "vkr.title")})
    Student studentDtoToStudent(StudentDto studentDto);
}
