package com.example.book_store_user_account.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull @NotEmpty @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{3,10}$", message = "Change your name")
    private String name;

    @NotNull @NotEmpty @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_-]{5,15}$", message = "Change your username")
    private String username;

    @NotNull @NotEmpty @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,16}", message = "To simple password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
