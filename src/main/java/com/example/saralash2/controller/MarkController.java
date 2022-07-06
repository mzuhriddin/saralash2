package com.example.saralash2.controller;

import com.example.saralash2.dto.ApiResponse;
import com.example.saralash2.dto.MarkDto;
import com.example.saralash2.dto.MarkDto;
import com.example.saralash2.entity.Mark;
import com.example.saralash2.entity.Mark;
import com.example.saralash2.repository.MarkRepository;
import com.example.saralash2.service.MarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/mark")
@RequiredArgsConstructor
public class MarkController {
    private final MarkService markService;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(markService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Integer id) {
        ApiResponse<Mark> response = markService.getOne(id);
        return ResponseEntity.status(response.isSuccess() ? 200:404).body(response);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody MarkDto markDto) {
        ApiResponse<Mark> response = markService.add(markDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Integer id, @RequestBody MarkDto markDto) {
        ApiResponse<Mark> response = markService.edit(id, markDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        ApiResponse response = markService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response.getMessage());
    }
}
