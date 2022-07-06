package com.example.saralash2.controller;

import com.example.saralash2.dto.ApiResponse;
import com.example.saralash2.dto.FacultyDto;
import com.example.saralash2.entity.Faculty;
import com.example.saralash2.repository.FacultyRepository;
import com.example.saralash2.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/faculty")
@RequiredArgsConstructor
public class FacultyController {
    private final FacultyRepository facultyRepository;
    private final FacultyService facultyService;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(facultyRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Integer id) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            return ResponseEntity.ok().body(optionalFaculty.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity add(@RequestBody FacultyDto facultyDto) {
        ApiResponse<Faculty> response = facultyService.add(facultyDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Integer id, @RequestBody FacultyDto facultyDto) {
        ApiResponse<Faculty> response = facultyService.edit(id, facultyDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        facultyRepository.delete(optionalFaculty.get());
        return ResponseEntity.ok("DELETED");
    }
}
