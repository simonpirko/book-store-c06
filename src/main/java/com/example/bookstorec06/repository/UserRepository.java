package com.example.bookstorec06.repository;

import com.example.bookstorec06.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional(readOnly = true)
    boolean existsByUsername(String username);

    @Transactional(readOnly = true)
    boolean existsByUsernameAndPassword(String username, String password);

    Optional<User> getByUsername(String username);

    @Modifying
    @Query("update User u set u.username = :newUsername where u.id = :id")
    void updateUsername(@Param("id") long id, @Param("newUsername") String newUsername);

    @Modifying
    @Query("update User u set u.password = :newPassword where u.id = :id")
    void updatePassword(@Param("id") long id, @Param("newPassword") String newPassword);

}
