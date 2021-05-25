package uz.sukhrob.testofepos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uz.sukhrob.testofepos.entity.Faculty;
import uz.sukhrob.testofepos.entity.University;
import uz.sukhrob.testofepos.payload.FacultyDto;
import uz.sukhrob.testofepos.payload.ResFaculty;
import uz.sukhrob.testofepos.payload.ResponseApi;
import uz.sukhrob.testofepos.repository.FacultyRepository;
import uz.sukhrob.testofepos.repository.UniversityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;


    public ResponseApi add(FacultyDto facultyDto) {
        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (!optionalUniversity.isPresent())
            return new ResponseApi("University not found", false);
        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        faculty.setUniversity(optionalUniversity.get());
        facultyRepository.save(faculty);
        return new ResponseApi("Successfully saved", true);
    }

    public ResponseApi delete(Long id) {
        try {
            facultyRepository.deleteById(id);
            return new ResponseApi("Successfully deleted", true);
        } catch (Exception e) {
            return new ResponseApi("Error in deleting", false);
        }
    }

    public ResponseApi edit(Long id, FacultyDto facultyDto) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (!optionalFaculty.isPresent()) return new ResponseApi("Not found", false);

        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (!optionalUniversity.isPresent())
            return new ResponseApi("Univeristy not found", false);
        Faculty faculty = optionalFaculty.get();
        faculty.setUniversity(optionalUniversity.get());
        faculty.setName(faculty.getName());
        facultyRepository.save(faculty);
        return new ResponseApi("Successfully edited", true);
    }

    public Page<ResFaculty> getAll(int page) {
        List<ResFaculty> list = new ArrayList<>();
        List<Faculty> all = facultyRepository.findAll();
        for (Faculty faculty : all) {
            ResFaculty resFaculty = new ResFaculty(
                    faculty.getName(),
                    faculty.getUniversity().getName()
            );
            list.add(resFaculty);
        }
        return new PageImpl<ResFaculty>(list, PageRequest.of(page, 15), list.size());

    }
}
