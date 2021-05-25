package uz.sukhrob.testofepos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sukhrob.testofepos.entity.University;

public interface UniversityRepository extends JpaRepository<University, Long> {

    boolean existsByName(String name);
}
