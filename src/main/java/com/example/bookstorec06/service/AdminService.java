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
    private final BookRepository bookRepository;

    public Optional<User> findById(long id) {
        return adminRepository.findById(id);
    }

    public void saveOrUpdateUser(User user) {
        adminRepository.save(user);
    }

    public List<User> findAllUser() {
        return adminRepository.findAll();
    }

    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    public Optional<Order> findOrderById(long id) {
        return orderRepository.findById(id);
    }

    public void deleteUserById(long id) {
        Optional<User> user = adminRepositoryfindById(id);
        if (user.isPresent()) {
            adminRepository.deleteById(id);
        } else {
            throw new MyNotFoundException("Wrong user id for deleteUserById " + id);
        }
    }

    public void deleteOrderById(long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.deleteById(id);
        } else {
            throw new MyNotFoundException("Wrong order id for deleteOrderById " + id);
        }
    }

    public void deleteBookById(long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new MyNotFoundException("Wrong book id for deleteBookById " + id);
        }
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(long id) {
        return bookRepository.findById(id);
    }
}
