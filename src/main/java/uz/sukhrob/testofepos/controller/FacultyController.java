package uz.sukhrob.testofepos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sukhrob.testofepos.entity.Faculty;
import uz.sukhrob.testofepos.payload.FacultyDto;
import uz.sukhrob.testofepos.payload.ResFaculty;
import uz.sukhrob.testofepos.payload.ResponseApi;
import uz.sukhrob.testofepos.service.FacultyService;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    FacultyService facultyService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody FacultyDto facultyDto) {
        ResponseApi responseApi = facultyService.add(facultyDto);
        if (responseApi.isSuccess()) return ResponseEntity.ok(responseApi);
        return ResponseEntity.status(409).body(responseApi);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable long id, @RequestBody FacultyDto facultyDto) {
        ResponseApi responseApi = facultyService.edit(id, facultyDto);
        if (responseApi.isSuccess()) return ResponseEntity.ok(responseApi);
        return ResponseEntity.status(409).body(responseApi);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        ResponseApi responseApi = facultyService.delete(id);
        if (responseApi.isSuccess()) return ResponseEntity.ok(responseApi);
        return ResponseEntity.status(409).body(responseApi);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getll(@RequestParam int page) {
        Page<ResFaculty> all = facultyService.getAll(page);
        if (!all.isEmpty()) return ResponseEntity.ok(all);
        return ResponseEntity.status(409).body(all);
    }

}
