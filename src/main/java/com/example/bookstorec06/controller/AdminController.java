package com.example.bookstorec06.controller;

import com.example.bookstorec06.entity.Role;
import com.example.bookstorec06.entity.User;
import com.example.bookstorec06.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("/role")
    public String updateRole(long id, Model model) {
        User userById = adminService.findById(id).get();
        if (userById.getRole().equals(Role.USER)) {
            userById.setRole(Role.ADMIN);
        } else {
            userById.setRole(Role.USER);
        }
        model.addAttribute("updateOk", "successfully updated user with id: " + id);
        adminService.saveOrUpdate(userById);
        return "updateRole";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> userList = adminService.findAllUser();
        model.addAttribute("userList", userList);
        return "userList";
    }

    @GetMapping("/orders")
    public String getAllOrders(Model model) {
        List<Order> orderList = adminService.findAllOrder();
        model.addAttribute("orderList", orderList);
        return "orderList";
    }

    @GetMapping("/remove")
    public String removePage() {
        return "adminRemove";
    }

    @PostMapping("/remove/user")
    public String removeUser(long id, Model model) {
        adminService.deleteUserById(id);
        model.addAttribute("removeUserOk", "successfully removed");
        return "adminRemove";
    }

    @PostMapping("/remove/order")
    public String removeOrder(long id, Model model) {
        adminService.deleteOrderById(id);
        model.addAttribute("removeOrderOk", "successfully removed");
        return "adminRemove";
    }

}






