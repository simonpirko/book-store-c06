package com.example.book_store_user_account.controller;

import com.example.book_store_user_account.entity.User;
import com.example.book_store_user_account.exception.UserNotFoundException;
import com.example.book_store_user_account.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@SessionAttributes("user")
@RequestMapping("/user/account")
public class UserAccountController {
    private final UserService userService;

    public UserAccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userAccount() {
        return "userAccount";
    }

    @GetMapping("/updateUsername")
    public String updateUsernameGet() {
        return "updateUsername";
    }

    @PostMapping("/updateUsername")
    public String updateUsername(String newUsername, @Valid User user, BindingResult bindingResult, Model model, SessionStatus session) {

        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                model.addAttribute("errorUsernameUpdate", fieldError.getDefaultMessage());
            }
            session.setComplete();
            return "updateUsername";
        }

        boolean existsNewUsername = userService.existsByUsername(newUsername);
        boolean existsUsernameAndPassword = userService.existsByUsernameAndPassword(user.getUsername(), user.getPassword());
        User userBySession = (User) model.getAttribute("user");
        if (userBySession == null) {
            session.setComplete();
            throw new UserNotFoundException("Something going wrong.Please sign in again and try one more time.");
        }
        if (!existsNewUsername & existsUsernameAndPassword) {
            userService.updateUsername(userBySession.getId(), newUsername);
            session.setComplete();
            model.addAttribute("reLogin", "Please sign in with new username or password");
            return "authorization";
        } else {
            model.addAttribute("ExistsUsernamePassword", "Username or Password is incorrect");
            return "updateUsername";
        }
    }

    @GetMapping("/updatePassword")
    public String updatePassword() {
        return "updatePassword";
    }

    @PostMapping("/updatePassword")
    public String updatePasswordPost(@Valid User user, BindingResult bindingResult, String newPassword, Model
            model, SessionStatus session) {
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                model.addAttribute("errorPassword", fieldError.getDefaultMessage());
            }
            session.setComplete();
            return "updatePassword";
        }

        boolean existsUser = userService.existsByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (existsUser) {
            User userByUsername = userService.getByUsername(user.getUsername()).get();
            userService.updatePassword(userByUsername.getId(), newPassword);
            session.setComplete();
            model.addAttribute("reLogin", "Please sign in with new username or password");
            return "authorization";
        } else {
            model.addAttribute("ExistsUsernamePassword", "Username or password is incorrect");
            return "updatePassword";
        }
    }

    @GetMapping("/history")
    public String userHistory() {

        //TODO userHistory;

        return "userHistory";
    }

    @GetMapping("/removeAccount")
    public String removeAccountGet() {
        return "removeAccount";
    }

    @PostMapping("/removeAccount")
    public String removeAccountPost(Model model, SessionStatus session) {
        User user = (User) model.getAttribute("user");
        if (user == null)
            throw new UserNotFoundException("Something going wrong.Please sign in again and try one more time.");
        userService.delete(user.getId());
        session.setComplete();
        return "home";
    }

    @PostMapping("/logout")
    public String logoutPost(SessionStatus session) {
        session.setComplete();
        return "home";
    }
}

