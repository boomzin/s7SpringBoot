package com.example.s7testtask.web;

import com.example.s7testtask.model.User;
import org.springframework.lang.NonNull;


public class AuthUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public AuthUser(@NonNull User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public int id() {
        return user.id();
    }

    public User getUser() {
        return this.user;
    }
}