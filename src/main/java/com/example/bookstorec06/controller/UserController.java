package com.example.bookstorec06.controller;

import com.example.bookstorec06.entity.Role;
import com.example.bookstorec06.entity.User;
import com.example.bookstorec06.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/reg")
    public ModelAndView getUserRegPage(ModelAndView modelAndView){
        modelAndView.addObject("newUser", new User());
        modelAndView.addObject("userExists", false);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/reg")
    public ModelAndView processUserRegistration(@Valid @ModelAttribute(name = "newUser") User newUser, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()){
            System.out.println("Binding errors");
            modelAndView.setViewName("registration");
        } else {
            if(userService.existsByUsername(newUser.getUsername())){
                modelAndView.addObject("userExists", true);
                modelAndView.setViewName("registration");
            } else {
                newUser.setRole(Role.USER);
                userService.save(newUser);
                modelAndView.addObject("authUser", new User());
                modelAndView.setViewName("authorization");
            }
        }
        return modelAndView;
    }

    @GetMapping("/auth")
    public ModelAndView getUserAuthPage(ModelAndView modelAndView){
        modelAndView.addObject("authUser", new User());
        modelAndView.addObject("isDataValid", true);
        modelAndView.setViewName("authorization");
        return modelAndView;
    }

    @PostMapping("/auth")
    public ModelAndView processUserAuthorization(@ModelAttribute(name = "authUser") User authUser, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Optional<User> byUsername = userService.findByUsername(authUser.getUsername());
        if (byUsername.isEmpty()){
            modelAndView.addObject("isDataValid", false);
            modelAndView.setViewName("authorization");
            return modelAndView;
        } else {
            User user = byUsername.get();
            if(user.getPassword().equals(authUser.getPassword())){
                session.setAttribute("user", user);
                //TODO перенаправлять пользователя после авторизации куда-то
                modelAndView.setViewName("/");
                return modelAndView;
            }
        }
        modelAndView.addObject("isDataValid", false);
        modelAndView.setViewName("authorization");
        return modelAndView;
    }
}
