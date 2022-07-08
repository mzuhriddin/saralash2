package com.example.saralash2.projection;

import com.example.saralash2.entity.Student;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "students", types = {Student.class})
public interface Students {
    String getName();

    float getPoint();
}
