package com.example.bookstorec06.service;

import com.example.bookstorec06.entity.User;
import com.example.bookstorec06.exception.MyNotFoundException;
import com.example.bookstorec06.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final OrderRepository orderRepository;

    public Optional<User> findById(long id) {
        return adminRepository.findById(id);
    }

    public void saveOrUpdate(User user) {
       adminRepository.save(user);
    }

    public List<User> findAllUser(){
      return adminRepository.findAll();
    }

    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }
    public void deleteUserById(long id){
        Optional<User> user = adminRepository.findById(id);
        if (user.isPresent()) {
            adminRepository.deleteById(id);
        }else {
            throw new MyNotFoundException("Wrong user id for deleteUserById " + id);
        }
    }

    public void deleteOrderById(long id){
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.deleteById(id);
        }else {
            throw new MyNotFoundException("Wrong order id for deleteOrderById " + id);
        }
    }

}
