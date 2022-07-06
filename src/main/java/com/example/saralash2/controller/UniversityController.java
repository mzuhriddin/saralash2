package com.example.saralash2.controller;

import com.example.saralash2.dto.ApiResponse;
import com.example.saralash2.dto.UniversityDto;
import com.example.saralash2.entity.University;
import com.example.saralash2.repository.UniversityRepository;
import com.example.saralash2.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/university")
@RequiredArgsConstructor
public class UniversityController {
    private final UniversityRepository universityRepository;
    private final UniversityService universityService;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(universityRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Integer id) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()) {
            return ResponseEntity.ok().body(optionalUniversity.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity add(@RequestBody UniversityDto universityDto) {
        ApiResponse<University> response = universityService.add(universityDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Integer id, @RequestBody UniversityDto universityDto) {
        ApiResponse<University> response = universityService.edit(id, universityDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        universityRepository.delete(optionalUniversity.get());
        return ResponseEntity.ok("Deleted");
    }
}
