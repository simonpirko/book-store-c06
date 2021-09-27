package com.example.book_store_user_account.service;

import com.example.book_store_user_account.entity.User;
import com.example.book_store_user_account.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);

    }

    public boolean existsByUsernameAndPassword(String username, String password) {
        return userRepository.existsByUsernameAndPassword(username, password);
    }

    public Optional<User> getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    public void updateUsername(long id, String newUsername){
        userRepository.updateUsername(id, newUsername);
    }

    public  void updatePassword(long id, String newPassword){
        userRepository.updatePassword(id, newPassword);
    }

    public void delete(long id){
        userRepository.deleteById(id);
    }

}
