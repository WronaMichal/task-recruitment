package com.wrona.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Builder
@Entity
@Setter
@AllArgsConstructor
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long distance;

    public Star(String name, Long distance) {
        this.name = name;
        this.distance = distance;
    }
}
