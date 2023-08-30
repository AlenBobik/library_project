package com.example.library_project.repository;

import com.example.library_project.entities.Obvestilo;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObvestiloRepository extends JpaRepository<Obvestilo, Long> {

    Optional<Obvestilo> findByObvestiloId(long id);

    Optional<Obvestilo> findByObvestiloDatum(Date datum);

    boolean existsByObvestiloId(long id);
}
