package com.example.saralash2.service;

import com.example.saralash2.dto.ApiResponse;
import com.example.saralash2.dto.MarkDto;
import com.example.saralash2.entity.Mark;
import com.example.saralash2.entity.Student;
import com.example.saralash2.entity.Subject;
import com.example.saralash2.repository.MarkRepository;
import com.example.saralash2.repository.StudentRepository;
import com.example.saralash2.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record MarkService(MarkRepository markRepository, StudentRepository studentRepository,
                          SubjectRepository subjectRepository) {

    public ApiResponse<Mark> add(MarkDto markDto) {
        Optional<Student> optionalStudent = studentRepository.findById(markDto.getStudentId());
        Optional<Subject> optionalSubject = subjectRepository.findById(markDto.getSubjectId());
        if (optionalStudent.isEmpty() || optionalSubject.isEmpty()) {
            return ApiResponse.<Mark>builder().message("NOT FOUND").build();
        }
        Mark save = markRepository.save(Mark.builder()
                .point(markDto.getPoint())
                .student(optionalStudent.get())
                .subject(optionalSubject.get())
                .build());
        return ApiResponse.<Mark>builder()
                .success(true)
                .object(save)
                .message("ADDED")
                .build();
    }

    public ApiResponse<Mark> edit(Integer id, MarkDto markDto) {
        Optional<Student> optionalStudent = studentRepository.findById(markDto.getStudentId());
        Optional<Subject> optionalSubject = subjectRepository.findById(markDto.getSubjectId());
        Optional<Mark> optionalMark = markRepository.findById(id);
        if (optionalStudent.isEmpty() || optionalSubject.isEmpty() || optionalMark.isEmpty()) {
            return ApiResponse.<Mark>builder().message("NOT FOUND").build();
        }
        Mark mark = optionalMark.get();
        mark.setPoint(markDto.getPoint());
        mark.setStudent(optionalStudent.get());
        mark.setSubject(optionalSubject.get());
        Mark save = markRepository.save(mark);
        return ApiResponse.<Mark>builder()
                .success(true)
                .object(save)
                .message("EDITED")
                .build();
    }

    public List<Mark> getAll() {
        return markRepository.findAll();
    }

    public ApiResponse<Mark> getOne(Integer id) {
        Optional<Mark> optionalMark = markRepository.findById(id);
        if (optionalMark.isPresent()) {
            return ApiResponse.<Mark>builder()
                    .success(true)
                    .object(optionalMark.get())
                    .build();
        }
        return ApiResponse.<Mark>builder()
                .message("NOT FOUND")
                .build();
    }

    public ApiResponse delete(Integer id) {
        Optional<Mark> optionalMark = markRepository.findById(id);
        if (optionalMark.isEmpty()) {
            return ApiResponse.builder()
                    .message("NOT FOUND")
                    .build();
        }
        markRepository.delete(optionalMark.get());
        return ApiResponse.builder()
                .message("DELETED")
                .success(true)
                .build();
    }
}
