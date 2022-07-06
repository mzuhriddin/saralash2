package com.example.saralash2.service;

import com.example.saralash2.dto.ApiResponse;
import com.example.saralash2.dto.SubjectDto;
import com.example.saralash2.dto.SubjectDto;
import com.example.saralash2.entity.Subject;
import com.example.saralash2.entity.Subject;
import com.example.saralash2.entity.Subject;
import com.example.saralash2.entity.Group;
import com.example.saralash2.repository.GroupRepository;
import com.example.saralash2.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record SubjectService(SubjectRepository subjectRepository, GroupRepository groupRepository) {
    public ApiResponse<Subject> add(SubjectDto subjectDto) {
        Optional<Group> optionalGroup = groupRepository.findById(subjectDto.getGroupId());
        if (optionalGroup.isEmpty()) {
            return ApiResponse.<Subject>builder().message("GROUP NOT FOUND").build();
        }
        Subject save = subjectRepository.save(Subject.builder()
                .group(optionalGroup.get())
                .name(subjectDto.getName())
                .build());
        return ApiResponse.<Subject>builder()
                .message("ADDED")
                .success(true)
                .object(save)
                .build();
    }

    public ApiResponse<Subject> edit(Integer id, SubjectDto subjectDto) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        Optional<Group> optionalGroup = groupRepository.findById(subjectDto.getGroupId());
        if (optionalGroup.isEmpty() || optionalSubject.isEmpty()) {
            return ApiResponse.<Subject>builder().message("NOT FOUND").build();
        }
        Subject subject = optionalSubject.get();
        subject.setGroup(optionalGroup.get());
        subject.setName(subjectDto.getName());
        Subject save = subjectRepository.save(subject);
        return ApiResponse.<Subject>builder()
                .message("EDITED")
                .object(save)
                .success(true)
                .build();
    }

    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    public ApiResponse<Subject> getOne(Integer id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            return ApiResponse.<Subject>builder()
                    .success(true)
                    .object(optionalSubject.get())
                    .build();
        }
        return ApiResponse.<Subject>builder()
                .message("NOT FOUND")
                .build();
    }

    public ApiResponse delete(Integer id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isEmpty()) {
            return ApiResponse.builder()
                    .message("NOT FOUND")
                    .build();
        }
        subjectRepository.delete(optionalSubject.get());
        return ApiResponse.builder()
                .message("DELETED")
                .success(true)
                .build();
    }
}
