package com.example.saralash2.projection;

import com.example.saralash2.entity.Subject;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "subjectView", types = {Subject.class})
public interface SubjectView {
    String getName();
}
