package com.example.library_project.repository;

import com.example.library_project.entities.KnjiznaPolica;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnjiznaPolicaRepository extends JpaRepository<KnjiznaPolica, Long> {

    List<KnjiznaPolica> findAll();

    Optional<KnjiznaPolica> findByKnjiznaPolicaOznaka(String oznaka);

    Optional<KnjiznaPolica> findByKnjiznaPolicaId(long id);

    boolean existsByKnjiznaPolicaOznaka(String oznaka);
}
