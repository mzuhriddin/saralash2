package com.example.saralash2.projection;

import com.example.saralash2.entity.Student;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "studentView", types = {Student.class})
public interface StudentView {
    String getName();

    String getGroup();

    int getYear();

    String getFaculty();
}
