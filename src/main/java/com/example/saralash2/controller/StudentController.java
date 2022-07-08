package com.example.saralash2.controller;

import com.example.saralash2.dto.ApiResponse;
import com.example.saralash2.dto.StudentDto;
import com.example.saralash2.entity.Student;
import com.example.saralash2.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Integer id) {
        ApiResponse<Student> response = studentService.getOne(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody StudentDto studentDto) {
        ApiResponse<Student> response = studentService.add(studentDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Integer id, @RequestBody StudentDto studentDto) {
        ApiResponse<Student> response = studentService.edit(id, studentDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        ApiResponse response = studentService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response.getMessage());
    }

    @GetMapping("/byName")
    public ResponseEntity getByName(@RequestParam String name) {
        return ResponseEntity.ok(studentService.getByName(name));
    }

    @GetMapping("/byGroupId")
    public ResponseEntity getByGroupId(@RequestParam Integer id) {
        return ResponseEntity.ok(studentService.getByGroupId(id));
    }
}
