package uz.sukhrob.testofepos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sukhrob.testofepos.payload.CourseDto;
import uz.sukhrob.testofepos.payload.ResCourse;
import uz.sukhrob.testofepos.payload.ResponseApi;
import uz.sukhrob.testofepos.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CourseDto courseDto) {
        ResponseApi responseApi = courseService.add(courseDto);
        if (responseApi.isSuccess()) return ResponseEntity.ok(responseApi);
        return ResponseEntity.status(409).body(responseApi);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        ResponseApi responseApi = courseService.edit(id,courseDto);
        if (responseApi.isSuccess()) return ResponseEntity.ok(responseApi);
        return ResponseEntity.status(409).body(responseApi);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ResponseApi responseApi = courseService.delete(id);
        if (responseApi.isSuccess()) return ResponseEntity.ok(responseApi);
        return ResponseEntity.status(409).body(responseApi);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam int page) {
        Page<ResCourse> all = courseService.getAll(page);
        if (!all.isEmpty()) return ResponseEntity.ok(all);
        return ResponseEntity.status(409).body(all);
    }


}
