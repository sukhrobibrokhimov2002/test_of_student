package uz.sukhrob.testofepos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sukhrob.testofepos.payload.ResStudent;
import uz.sukhrob.testofepos.payload.ResponseApi;
import uz.sukhrob.testofepos.payload.StudentDto;
import uz.sukhrob.testofepos.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService service;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody StudentDto studentDto) {
        ResponseApi responseApi = service.add(studentDto);
        if (responseApi.isSuccess())
            return ResponseEntity.ok(responseApi);
        return ResponseEntity.status(409).body(responseApi);

    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable long id,@RequestBody StudentDto studentDto) {
        ResponseApi responseApi = service.edit(id,studentDto);
        if (responseApi.isSuccess())
            return ResponseEntity.ok(responseApi);
        return ResponseEntity.status(409).body(responseApi);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable long id) {
        ResponseApi responseApi = service.delete(id);
        if (responseApi.isSuccess())
            return ResponseEntity.ok(responseApi);
        return ResponseEntity.status(409).body(responseApi);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll (@RequestParam int page) {
        Page<ResStudent> all = service.getAll(page);
        if (!all.isEmpty())
            return ResponseEntity.ok(all);
        return ResponseEntity.status(409).body(all);
    }
}
