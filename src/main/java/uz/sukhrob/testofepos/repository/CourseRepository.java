package uz.sukhrob.testofepos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sukhrob.testofepos.entity.Courses;

public interface CourseRepository extends JpaRepository<Courses,Long> {
    boolean existsByCourseNumber(String courseNumber);
}
