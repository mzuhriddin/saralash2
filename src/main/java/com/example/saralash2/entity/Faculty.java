package com.example.saralash2.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false, columnDefinition = "text")
    private String name;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private University university;

}
