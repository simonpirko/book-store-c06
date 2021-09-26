package com.example.bookstorec06.repository;

import com.example.bookstorec06.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    Optional<User> getByUsername(String username);
}
