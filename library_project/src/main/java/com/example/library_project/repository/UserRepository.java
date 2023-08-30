package com.example.library_project.repository;

import com.example.library_project.entities.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    Optional<User> findByUsername(String ime);

    Optional<User> findByOsebaOsebaEmail(String email);

    boolean existsByOsebaOsebaEmail(String email);

    boolean existsByUsername(String username);
}
