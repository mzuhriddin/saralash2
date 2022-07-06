package com.example.saralash2.service;

import com.example.saralash2.dto.ApiResponse;
import com.example.saralash2.dto.FacultyDto;
import com.example.saralash2.entity.Faculty;
import com.example.saralash2.entity.University;
import com.example.saralash2.repository.FacultyRepository;
import com.example.saralash2.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record FacultyService(FacultyRepository facultyRepository, UniversityRepository universityRepository) {
    public ApiResponse<Faculty> add(FacultyDto facultyDto) {
        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (optionalUniversity.isEmpty()) {
            return ApiResponse.<Faculty>builder().message("UNIVERSITY NOT FOUND").build();
        }
        Faculty save = facultyRepository.save(Faculty.builder()
                .university(optionalUniversity.get())
                .name(facultyDto.getName())
                .build());
        return ApiResponse.<Faculty>builder()
                .message("ADDED")
                .success(true)
                .object(save)
                .build();
    }

    public ApiResponse<Faculty> edit(Integer id, FacultyDto facultyDto) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (optionalUniversity.isEmpty() || optionalFaculty.isEmpty()) {
            return ApiResponse.<Faculty>builder().message("NOT FOUND").build();
        }
        Faculty faculty = optionalFaculty.get();
        faculty.setUniversity(optionalUniversity.get());
        faculty.setName(facultyDto.getName());
        Faculty save = facultyRepository.save(faculty);
        return ApiResponse.<Faculty>builder()
                .message("EDITED")
                .object(save)
                .success(true)
                .build();
    }

    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    public ApiResponse<Faculty> getOne(Integer id) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            return ApiResponse.<Faculty>builder()
                    .success(true)
                    .object(optionalFaculty.get())
                    .build();
        }
        return ApiResponse.<Faculty>builder()
                .message("NOT FOUND")
                .build();
    }

    public ApiResponse delete(Integer id) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isEmpty()) {
            return ApiResponse.builder()
                    .message("NOT FOUND")
                    .build();
        }
        facultyRepository.delete(optionalFaculty.get());
        return ApiResponse.builder()
                .message("DELETED")
                .success(true)
                .build();
    }
}
