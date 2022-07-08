package com.example.saralash2.projection;

import com.example.saralash2.entity.Group;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "groupView", types = {Group.class})
public interface GroupView {
    String getName();

    int getCount();
}
