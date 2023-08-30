package com.example.library_project.repository;

import com.example.library_project.entities.KnjigaIzvod;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnjigaIzvodRepository extends JpaRepository<KnjigaIzvod, Long> {

    List<KnjigaIzvod> findAll();

    Optional<KnjigaIzvod> findByKnjigaIzvodIsbn(String isbn);

    List<KnjigaIzvod> findAllByKnjigaIzvodNaslov(String ime);

    boolean existsByKnjigaIzvodIsbn(String isbn);
}
