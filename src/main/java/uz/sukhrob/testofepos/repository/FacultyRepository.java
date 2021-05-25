package uz.sukhrob.testofepos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sukhrob.testofepos.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
}
