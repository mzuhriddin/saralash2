package com.example.saralash2.controller;

import com.example.saralash2.dto.ApiResponse;
import com.example.saralash2.dto.GroupDto;
import com.example.saralash2.entity.Group;
import com.example.saralash2.repository.GroupRepository;
import com.example.saralash2.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupRepository groupRepository;
    private final GroupService groupService;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(groupRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Integer id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            return ResponseEntity.ok().body(optionalGroup.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity add(@RequestBody GroupDto groupDto) {
        ApiResponse<Group> response = groupService.add(groupDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Integer id, @RequestBody GroupDto groupDto) {
        ApiResponse<Group> response = groupService.edit(id, groupDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        groupRepository.delete(optionalGroup.get());
        return ResponseEntity.ok("Deleted");
    }
}
