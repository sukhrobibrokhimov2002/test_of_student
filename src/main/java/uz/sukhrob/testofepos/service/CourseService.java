package uz.sukhrob.testofepos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.sukhrob.testofepos.entity.Courses;
import uz.sukhrob.testofepos.entity.Faculty;
import uz.sukhrob.testofepos.payload.CourseDto;
import uz.sukhrob.testofepos.payload.ResCourse;
import uz.sukhrob.testofepos.payload.ResponseApi;
import uz.sukhrob.testofepos.repository.CourseRepository;
import uz.sukhrob.testofepos.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    FacultyRepository facultyRepository;


    public ResponseApi add(CourseDto courseDto) {
        boolean existsByCourseNumber = courseRepository.existsByCourseNumber(courseDto.getCourseNumber());
        if (existsByCourseNumber)
            return new ResponseApi("Course already exists9", false);
        Optional<Faculty> optionalFaculty = facultyRepository.findById(courseDto.getFacultyId());
        if (!optionalFaculty.isPresent())
            return new ResponseApi("Faculty not found", false);
        Courses courses = new Courses();
        courses.setCourseNumber(courseDto.getCourseNumber());
        courses.setFaculty(optionalFaculty.get());
        return new ResponseApi("Course successfully added", true);
    }

    public ResponseApi delete(Long id) {
        try {
            courseRepository.deleteById(id);
            return new ResponseApi("Successfully deleted", true);
        } catch (Exception e) {
            return new ResponseApi("Error in deleting", false);
        }
    }

    public ResponseApi edit(Long id, CourseDto courseDto) {
        Optional<Courses> optionalCourses = courseRepository.findById(id);
        if (!optionalCourses.isPresent())
            return new ResponseApi("Course not found", false);
        Optional<Faculty> optionalFaculty = facultyRepository.findById(courseDto.getFacultyId());
        if (!optionalFaculty.isPresent())
            return new ResponseApi("Faculty not found", false);
        Courses courses = optionalCourses.get();
        courses.setFaculty(optionalFaculty.get());
        courses.setCourseNumber(courseDto.getCourseNumber());
        courseRepository.save(courses);
        return new ResponseApi("Successfully edited", true);
    }

    public Page<ResCourse> getAll(int page) {
        List<Courses> all = courseRepository.findAll();
        List<ResCourse> courseList = new ArrayList<>();

        for (Courses courses : all) {
            ResCourse resCourse = new ResCourse(
                    courses.getCourseNumber(),
                    courses.getFaculty().getName()
            );
            courseList.add(resCourse);
        }
        return new PageImpl<ResCourse>(courseList, PageRequest.of(page, 15), courseList.size());
    }
}
