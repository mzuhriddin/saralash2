package com.example.saralash2.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

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

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Student student;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Subject subject;
}
