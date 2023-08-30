package com.example.library_project.repository;

import com.example.library_project.entities.Oseba;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OsebaRepository extends JpaRepository<Oseba, Long> {

    List<Oseba> findAll();

    Optional<Oseba> findByOsebaEmso(long emso);

    Optional<Oseba> findByOsebaEmail(String email);

    boolean existsByOsebaEmso(long emso);

    boolean existsByOsebaEmail(String email);

    boolean existsByOsebaTelefon(String telefon);
}
