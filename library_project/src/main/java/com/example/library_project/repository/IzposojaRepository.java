package com.example.library_project.repository;

import com.example.library_project.entities.Izposoja;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IzposojaRepository extends JpaRepository<Izposoja, Long> {

    List<Izposoja> findAll();

    List<Izposoja> findAllByUserUsername(String email);

    Optional<Izposoja> findByIzposojaId(long id);
}
