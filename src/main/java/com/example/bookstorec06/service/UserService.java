package com.example.bookstorec06.service;

import com.example.bookstorec06.entity.Role;
import com.example.bookstorec06.entity.User;
import com.example.bookstorec06.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public Optional<User> findById(long id){
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username){
        if(username==null || username.isBlank() || username.isEmpty()){
            return Optional.empty();
        } else {
            return userRepository.getByUsername(username);
        }
    }

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public void save(User user){
        userRepository.save(user);
    }

    public void setRole(long id, Role role){
        User byId = userRepository.getById(id);
        byId.setRole(role);
        userRepository.save(byId);
    }
}
