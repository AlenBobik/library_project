package com.example.library_project.repository;

import com.example.library_project.entities.Avtor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvtorRepository extends JpaRepository<Avtor, Long> {

    List<Avtor> findAll();

    Optional<Avtor> findByAvtorId(long id);

    boolean existsByAvtorId(long id);
}
