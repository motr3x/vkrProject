package ru.verevka.vkr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.verevka.vkr.domain.Vkr;

import java.util.Optional;

@Repository
public interface VkrRepository extends JpaRepository<Vkr,Long> {
    Optional<Vkr> findByTitle(String title);
    @Query(value = "DELETE FROM vkr WHERE supervisors_id is null;", nativeQuery = true)
    @Modifying
    void removeIfSupervisorIsNull();
}
