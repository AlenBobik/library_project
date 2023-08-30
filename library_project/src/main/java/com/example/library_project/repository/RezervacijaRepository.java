package com.example.library_project.repository;

import com.example.library_project.entities.Rezervacija;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {

    List<Rezervacija> findAll();

    List<Rezervacija> findAllByUserUsername(String email);

    Optional<Rezervacija> findByRezervacijaId(long id);
}
