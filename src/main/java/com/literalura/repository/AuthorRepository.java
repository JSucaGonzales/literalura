package com.literalura.repository;

import com.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Encontrar autores vivos (sin fecha de muerte)
    List<Author> findByDeathDateIsNull();

    // Encontrar autores que estaban vivos en un rango de fechas
    List<Author> findByBirthDateBeforeAndDeathDateAfter(LocalDate start, LocalDate end);

    // Buscar un autor por nombre
    Optional<Author> findByName(String name);
}
