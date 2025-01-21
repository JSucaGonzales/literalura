package com.literalura.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "birth_date")
    private int birthDate;

    @Column(name = "death_date")
    private Integer deathDate;

    public void setBirthYear(int birthYear) {
        this.birthDate = birthYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathDate = deathYear;
    }

}
