package com.example.saralash2.controller;

import com.example.saralash2.dto.ApiResponse;
import com.example.saralash2.dto.StudentDto;
import com.example.saralash2.entity.Student;
import com.example.saralash2.repository.StudentRepository;
import com.example.saralash2.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(studentRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            return ResponseEntity.ok().body(optionalStudent.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity add(@RequestBody StudentDto studentDto) {
        ApiResponse response = studentService.add(studentDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Integer id, @RequestBody StudentDto studentDto) {
        ApiResponse response = studentService.edit(id, studentDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        studentRepository.delete(optionalStudent.get());
        return ResponseEntity.ok("Deleted");
    }
}
