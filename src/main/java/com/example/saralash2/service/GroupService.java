package com.example.saralash2.service;

import com.example.saralash2.dto.ApiResponse;
import com.example.saralash2.dto.GroupDto;
import com.example.saralash2.entity.Faculty;
import com.example.saralash2.entity.Group;
import com.example.saralash2.projection.GroupView;
import com.example.saralash2.repository.FacultyRepository;
import com.example.saralash2.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record GroupService(GroupRepository groupRepository, FacultyRepository facultyRepository) {

    public ApiResponse<Group> add(GroupDto groupDto) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (optionalFaculty.isEmpty()) {
            return ApiResponse.<Group>builder().message("FACULTY NOT FOUND").build();
        }
        Group save = groupRepository.save(Group.builder()
                .faculty(optionalFaculty.get())
                .name(groupDto.getName())
                .year(groupDto.getYear())
                .build());
        return ApiResponse.<Group>builder()
                .message("ADDED")
                .success(true)
                .object(save)
                .build();
    }

    public ApiResponse<Group> edit(Integer id, GroupDto groupDto) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isEmpty() || optionalFaculty.isEmpty()) {
            return ApiResponse.<Group>builder().message("NOT FOUND").build();
        }
        Group group = optionalGroup.get();
        group.setFaculty(optionalFaculty.get());
        group.setName(groupDto.getName());
        group.setYear(groupDto.getYear());
        Group save = groupRepository.save(group);
        return ApiResponse.<Group>builder()
                .message("EDITED")
                .object(save)
                .success(true)
                .build();
    }

    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    public ApiResponse<Group> getOne(Integer id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            return ApiResponse.<Group>builder()
                    .success(true)
                    .object(optionalGroup.get())
                    .build();
        }
        return ApiResponse.<Group>builder()
                .message("NOT FOUND")
                .build();
    }

    public ApiResponse delete(Integer id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isEmpty()) {
            return ApiResponse.builder()
                    .message("NOT FOUND")
                    .build();
        }
        groupRepository.delete(optionalGroup.get());
        return ApiResponse.builder()
                .message("DELETED")
                .success(true)
                .build();
    }

    public List<GroupView> getGroupAndStudents(Integer facultyId) {
        return groupRepository.getGroup(facultyId);
    }
}
