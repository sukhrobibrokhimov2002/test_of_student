package uz.sukhrob.testofepos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sukhrob.testofepos.entity.University;
import uz.sukhrob.testofepos.payload.ReqChangeUniversityName;
import uz.sukhrob.testofepos.payload.ResUniversity;
import uz.sukhrob.testofepos.payload.ResponseApi;
import uz.sukhrob.testofepos.payload.UniversityDto;
import uz.sukhrob.testofepos.service.UniversityService;

@RestController
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    UniversityService universityService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody UniversityDto universityDto) {
        ResponseApi responseApi = universityService.add(universityDto);
        if (responseApi.isSuccess()) return ResponseEntity.ok(responseApi);
        return ResponseEntity.status(409).body(responseApi);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id,@RequestBody ReqChangeUniversityName universityDto) {
        ResponseApi responseApi = universityService.edit(id,universityDto);
        if (responseApi.isSuccess()) return ResponseEntity.ok(responseApi);
        return ResponseEntity.status(409).body(responseApi);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ResponseApi responseApi = universityService.delete(id);
        if (responseApi.isSuccess()) return ResponseEntity.ok(responseApi);
        return ResponseEntity.status(409).body(responseApi);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam int page) {
        Page<ResUniversity> universities = universityService.getUniversities(page);

        if (!universities.isEmpty()) return ResponseEntity.ok(universities);
        return ResponseEntity.status(409).body(universities);
    }
}
