package com.example.bookstorec06.repository;

import com.example.bookstorec06.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<User, Long> {

}
