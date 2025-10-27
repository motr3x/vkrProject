package ru.verevka.vkr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.verevka.vkr.domain.Student;
import ru.verevka.vkr.domain.Supervisors;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupervisorsRepository extends JpaRepository<Supervisors, Long> {
    @Query(value = "SELECT student.* FROM student INNER JOIN vkr ON student.vkr_id = vkr.id AND vkr.supervisors_id=?1", nativeQuery = true)
    Optional<List<Student>> getAllStudentById(Long id);
}
