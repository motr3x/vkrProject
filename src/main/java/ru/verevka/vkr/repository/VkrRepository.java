package ru.verevka.vkr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.verevka.vkr.domain.Vkr;

@Repository
public interface VkrRepository extends JpaRepository<Vkr,Long> {
}
