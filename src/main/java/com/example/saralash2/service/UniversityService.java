package com.example.saralash2.service;

import com.example.saralash2.dto.ApiResponse;
import com.example.saralash2.dto.UniversityDto;
import com.example.saralash2.entity.University;
import com.example.saralash2.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record UniversityService(UniversityRepository universityRepository) {
    public ApiResponse<University> add(UniversityDto universityDto) {
        University save = universityRepository.save(University.builder()
                .address(universityDto.getAddress())
                .name(universityDto.getName())
                .openYear(universityDto.getOpenYear())
                .build());
        return ApiResponse.<University>builder()
                .message("ADDED")
                .object(save)
                .success(true)
                .build();
    }

    public ApiResponse<University> edit(Integer id, UniversityDto universityDto) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isEmpty()) {
            return ApiResponse.<University>builder()
                    .message("UNIVERSITY NOT FOUND")
                    .build();
        }
        University university = optionalUniversity.get();
        university.setAddress(universityDto.getAddress());
        university.setName(universityDto.getName());
        university.setOpenYear(universityDto.getOpenYear());
        University save = universityRepository.save(university);
        return ApiResponse.<University>builder()
                .message("EDITED")
                .success(true)
                .object(save)
                .build();
    }

    public List<University> getAll() {
        return universityRepository.findAll();
    }

    public ApiResponse<University> getOne(Integer id) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()) {
            return ApiResponse.<University>builder()
                    .success(true)
                    .object(optionalUniversity.get())
                    .build();
        }
        return ApiResponse.<University>builder()
                .message("NOT FOUND")
                .build();
    }

    public ApiResponse delete(Integer id) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isEmpty()) {
            return ApiResponse.builder()
                    .message("NOT FOUND")
                    .build();
        }
        universityRepository.delete(optionalUniversity.get());
        return ApiResponse.builder()
                .message("DELETED")
                .success(true)
                .build();
    }
}
