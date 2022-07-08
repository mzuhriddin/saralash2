package com.example.saralash2.repository;

import com.example.saralash2.entity.Group;
import com.example.saralash2.projection.GroupView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    @Query(value = "select g.name as name, count(s) as count from groups as g " +
            "join student as s on g.id = s.group_id " +
            "where faculty_id = :id " +
            "group by g.name",
            nativeQuery = true)
    List<GroupView> getGroup(Integer id);

}
