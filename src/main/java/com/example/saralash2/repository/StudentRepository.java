package com.example.saralash2.repository;

import com.example.saralash2.entity.Student;
import com.example.saralash2.projection.StudentView;
import com.example.saralash2.projection.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = """
            select s.name as name, g.name as "group", g.year as year, f.name as faculty from student s
            join groups g on g.id = s.group_id
            join faculty f on f.id = g.faculty_id
            where s.name = :name""", nativeQuery = true)
    List<StudentView> getStudentByName(String name);

    @Query(value = """
            select s.name, avg(m.point) as point from student s
            join mark m on s.id = m.student_id
            where group_id = :id
            group by s.name
            order by point desc""", nativeQuery = true)
    List<Students> getStudentByGroupId(Integer id);
}
