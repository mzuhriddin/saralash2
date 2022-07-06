package com.example.saralash2.repository;

import com.example.saralash2.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query(name = "",nativeQuery = true)
    void deleteById(Integer id);
}
