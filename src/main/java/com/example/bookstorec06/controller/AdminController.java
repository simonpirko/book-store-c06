package com.example.bookstorec06.controller;

import com.example.bookstorec06.entity.Role;
import com.example.bookstorec06.entity.User;
import com.example.bookstorec06.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/role")
    public String updateRolePage() {
        return "updateRole";
    }

    @PostMapping("/role/update")
    public String updateRole(long id, Model model) {
        User userById = adminService.findById(id).get();
        if (userById.getRole().equals(Role.USER)) {
            userById.setRole(Role.ADMIN);
        } else {
            userById.setRole(Role.USER);
        }
        model.addAttribute("updateOk", "successfully updated user with id: " + id);
        adminService.saveOrUpdateUser(userById);
        return "updateRole";
    }

    @GetMapping("/user/list")
    public String getAllUsers(Model model) {
        List<User> userList = adminService.findAllUser();
        model.addAttribute("userList", userList);
        return "userList";
    }

    @GetMapping("/order/list")
    public String getAllOrders(Model model) {
        List<Order> orderList = adminService.findAllOrder();
        model.addAttribute("orderList", orderList);
        return "orderList";
    }

    @GetMapping("/book/list")
    public String getAllBooks(Model model) {
        List<Book> bookList = adminService.findAllBook();
        model.addAttribute("bookList", bookList);
        return "bookList";
    }

    @GetMapping("/remove")
    public String removePage() {
        return "adminRemove";
    }

    @PostMapping("/remove/user")
    public String removeUser(long id, Model model) {
        adminService.deleteUserById(id);
        model.addAttribute("removeUserOk", "user successfully remove");
        return "adminRemove";
    }

    @PostMapping("/remove/order")
    public String removeOrder(long id, Model model) {
        adminService.deleteOrderById(id);
        model.addAttribute("removeOrderOk", "Order successfully remove");
        return "adminRemove";
    }

    @PostMapping("/remove/book")
    public String removeBook(long id, Model model) {
        adminService.deleteBookById(id);
        model.addAttribute("removeBookOk", "Book successfully remove");
        return "adminRemove";
    }

    @GetMapping("/book")
    public String adminBookPage() {
        return "adminBook";
    }

    @PostMapping("/book/save")
    public String saveBook(@Valid Book book, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                model.addAttribute("saveBook", fieldError.getDefaultMessage());
            }
            return "adminBook";
        }
        adminService.saveBook(book);
        model.addAttribute("saveOk", "Book have been savedOrUpdated " + book);
        return "adminBook";
    }

    @PostMapping("/book/update")
    public String updateBook(long id, @Valid Book newBook, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                model.addAttribute("updateBook", fieldError.getDefaultMessage());
            }
            return "adminBook";
        }
        Book bookById = adminService.findBookById(id).get();
        bookById.setAuthor(newBook.getAuthor);
        bookById.setBookName(newBook.getBookName);
        bookById.setDescription(newBook.getDescription);
        bookById.setPrice(newBook.getPrice);
        adminService.saveBook(bookById);
        model.addAttribute("updateOk", "Book have been savedOrUpdated " + bookById);
        return "adminBook";
    }

}






