package com.example.library_project.repository;

import com.example.library_project.entities.Zaposlen;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZaposlenRepository extends JpaRepository<Zaposlen, Long> {

    List<Zaposlen> findAll();

    Optional<Zaposlen> findByZaposlenOznakaPogodbe(String oznaka);

    boolean existsByZaposlenOznakaPogodbe(String oznaka);
}
