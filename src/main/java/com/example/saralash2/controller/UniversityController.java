package com.example.saralash2.controller;

import com.example.saralash2.dto.ApiResponse;
import com.example.saralash2.dto.UniversityDto;
import com.example.saralash2.entity.University;
import com.example.saralash2.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/university")
@RequiredArgsConstructor
public class UniversityController {
    private final UniversityService universityService;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(universityService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Integer id) {
        ApiResponse<University> response = universityService.getOne(id);
        return ResponseEntity.status(response.isSuccess() ? 200:404).body(response);
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
        ApiResponse response = universityService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response.getMessage());
    }
}
