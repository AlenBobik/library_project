package com.example.library_project.repository;

import com.example.library_project.entities.Knjiga;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnjigaRepository extends JpaRepository<Knjiga, Long> {

    List<Knjiga> findAll();

    Optional<Knjiga> findByKnjigaUuid(UUID uuid);

    boolean existsByKnjigaUuid(UUID uuid);
}
