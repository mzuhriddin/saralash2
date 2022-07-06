package com.example.saralash2.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private int point;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Student student;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Subject subject;
}
