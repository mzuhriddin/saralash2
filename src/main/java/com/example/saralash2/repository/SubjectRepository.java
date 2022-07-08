package com.example.saralash2.repository;

import com.example.saralash2.entity.Subject;
import com.example.saralash2.projection.SubjectView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query(value = """
            select s.id, s.name from subject s
            where group_id = (select group_id from student
                                where student.id = :id)""", nativeQuery = true)
    List<SubjectView> getSubjects(Integer id);
}
