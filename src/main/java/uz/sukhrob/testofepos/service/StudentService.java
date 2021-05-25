package uz.sukhrob.testofepos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.sukhrob.testofepos.entity.Address;
import uz.sukhrob.testofepos.entity.Student;
import uz.sukhrob.testofepos.payload.ResStudent;
import uz.sukhrob.testofepos.payload.ResponseApi;
import uz.sukhrob.testofepos.payload.StudentDto;
import uz.sukhrob.testofepos.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;


    public ResponseApi add(StudentDto studentDto) {
        Address address = new Address(
                studentDto.getAddress().getCity(),
                studentDto.getAddress().getStreet(),
                studentDto.getAddress().getHomeNumber()
        );

        Student student = new Student(
                studentDto.getFullName(),
                address
        );
        studentRepository.save(student);
        return new ResponseApi("Successfully added", true);

    }

    public ResponseApi edit(Long id, StudentDto studentDto) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent()) return new ResponseApi("Not found", false);
        Student student = optionalStudent.get();
        Address address = new Address(
                student.getAddress().getCity(),
                student.getAddress().getStreet(),
                student.getAddress().getHomeNumber()
        );
        student.setAddress(address);
        student.setFullName(studentDto.getFullName());
        studentRepository.save(student);
        return new ResponseApi("Successfully edited", true);
    }

    public ResponseApi delete(Long id) {
        try {
            studentRepository.deleteById(id);
            return new ResponseApi("Successfully added", true);
        } catch (Exception e) {
            return new ResponseApi("Error in deleting", false);
        }
    }

    public Page<ResStudent> getAll(int page) {
        List<Student> all = studentRepository.findAll();
        List<ResStudent> resStudentList = new ArrayList<>();
        for (Student student : all) {
            ResStudent resStudent = new ResStudent(
                    student.getFullName(),
                    student.getAddress().getCity(),
                    student.getAddress().getStreet(),
                    student.getAddress().getHomeNumber()
            );
            resStudentList.add(resStudent);
        }
        return new PageImpl<ResStudent>(resStudentList, PageRequest.of(page, 15), resStudentList.size());

    }
}
