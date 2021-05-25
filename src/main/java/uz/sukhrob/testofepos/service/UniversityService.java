package uz.sukhrob.testofepos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.sukhrob.testofepos.entity.Address;
import uz.sukhrob.testofepos.entity.University;
import uz.sukhrob.testofepos.payload.ReqChangeUniversityName;
import uz.sukhrob.testofepos.payload.ResUniversity;
import uz.sukhrob.testofepos.payload.ResponseApi;
import uz.sukhrob.testofepos.payload.UniversityDto;
import uz.sukhrob.testofepos.repository.UniversityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {
    @Autowired
    UniversityRepository universityRepository;


    public ResponseApi add(UniversityDto universityDto) {
        boolean existsByName = universityRepository.existsByName(universityDto.getName());
        if (existsByName) return new ResponseApi("University already exists", false);
        Address address = new Address();
        address.setCity(universityDto.getAddressDto().getCity());
        address.setStreet(universityDto.getAddressDto().getStreet());
        address.setHomeNumber(universityDto.getAddressDto().getHomeNumber());
        University university = new University();
        university.setAddress(address);
        universityRepository.save(university);
        return new ResponseApi("Successfully saved", true);
    }


    public ResponseApi edit(Long id, ReqChangeUniversityName reqChangeUniversityName) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (!optionalUniversity.isPresent())
            return new ResponseApi("Not found", false);
        University university = optionalUniversity.get();
        university.setName(reqChangeUniversityName.getNewName());
        universityRepository.save(university);
        return new ResponseApi("Successfully changed", true);

    }

    public ResponseApi delete(Long id) {
        try {
            universityRepository.deleteById(id);
            return new ResponseApi("Successfully deleted", true);
        } catch (Exception e) {
            return new ResponseApi("Error in deleting ", false);
        }
    }

    public Page<ResUniversity> getUniversities(int page) {
        List<ResUniversity> universityList = new ArrayList<>();
        List<University> all = universityRepository.findAll();
        for (University university : all) {
            ResUniversity resUniversity = new ResUniversity(
                    university.getName(),
                    university.getAddress().getCity(),
                    university.getAddress().getStreet(),
                    university.getAddress().getHomeNumber()
            );
            universityList.add(resUniversity);
        }
        return new PageImpl<ResUniversity>(universityList, PageRequest.of(page, 15), universityList.size());

    }
}
