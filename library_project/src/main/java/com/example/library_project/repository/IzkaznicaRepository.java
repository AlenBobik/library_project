package com.example.library_project.repository;

import com.example.library_project.entities.Izkaznica;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IzkaznicaRepository extends JpaRepository<Izkaznica, Long> {

    List<Izkaznica> findAll();

    Optional<Izkaznica> findByIzkaznicaOznaka(String oznaka);

    boolean existsByIzkaznicaOznaka(String oznaka);
}
