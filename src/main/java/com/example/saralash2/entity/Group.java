package com.example.saralash2.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@Entity(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false, columnDefinition = "text")
    private String name;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Faculty faculty;

    private int year;
}
