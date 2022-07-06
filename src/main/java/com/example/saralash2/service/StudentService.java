package com.example.saralash2.service;

import com.example.saralash2.dto.ApiResponse;
import com.example.saralash2.dto.StudentDto;
import com.example.saralash2.entity.Group;
import com.example.saralash2.entity.Student;
import com.example.saralash2.repository.GroupRepository;
import com.example.saralash2.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record StudentService(StudentRepository studentRepository, GroupRepository groupRepository) {

    public ApiResponse<Student> add(StudentDto studentDto) {
        Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());
        if (optionalGroup.isEmpty()) {
            return ApiResponse.<Student>builder().message("GROUP NOT FOUND").build();
        }
        Student save = studentRepository.save(Student.builder()
                .group(optionalGroup.get())
                .name(studentDto.getName())
                .build());
        return ApiResponse.<Student>builder()
                .message("ADDED")
                .success(true)
                .object(save)
                .build();
    }

    public ApiResponse<Student> edit(Integer id, StudentDto studentDto) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());
        if (optionalGroup.isEmpty() || optionalStudent.isEmpty()) {
            return ApiResponse.<Student>builder().message("NOT FOUND").build();
        }
        Student student = optionalStudent.get();
        student.setGroup(optionalGroup.get());
        student.setName(studentDto.getName());
        Student save = studentRepository.save(student);
        return ApiResponse.<Student>builder()
                .message("EDITED")
                .object(save)
                .success(true)
                .build();
    }
}
