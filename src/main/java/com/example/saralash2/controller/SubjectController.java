package com.example.saralash2.controller;

import com.example.saralash2.dto.ApiResponse;
import com.example.saralash2.dto.SubjectDto;
import com.example.saralash2.entity.Subject;
import com.example.saralash2.repository.SubjectRepository;
import com.example.saralash2.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectRepository subjectRepository;
    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(subjectRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Integer id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            return ResponseEntity.ok(optionalSubject.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity add(@RequestBody SubjectDto subjectDto) {
        ApiResponse<Subject> response = subjectService.add(subjectDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Integer id, @RequestBody SubjectDto subjectDto) {
        ApiResponse<Subject> response = subjectService.edit(id, subjectDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        subjectRepository.delete(optionalSubject.get());
        return ResponseEntity.ok("Deleted");
    }
}
