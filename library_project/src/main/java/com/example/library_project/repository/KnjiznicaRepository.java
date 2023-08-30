package com.example.library_project.repository;

import com.example.library_project.entities.Knjiznica;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnjiznicaRepository extends JpaRepository<Knjiznica, Long> {

    List<Knjiznica> findAll();

    Optional<Knjiznica> findByKnjiznicaId(long knjiznica_id);

    boolean existsByKnjiznicaId(long knjiznica_id);
}
