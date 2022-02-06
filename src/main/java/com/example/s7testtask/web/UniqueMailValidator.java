package com.example.s7testtask.web;

import com.example.s7testtask.error.IllegalRequestDataException;
import com.example.s7testtask.model.User;
import com.example.s7testtask.repository.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;

@Component
public class UniqueMailValidator implements org.springframework.validation.Validator {

    private final UserRepository repository;
    private final HttpServletRequest request;

    public UniqueMailValidator(UserRepository repository, HttpServletRequest request) {
        this.repository = repository;
        this.request = request;
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        User user = ((User) target);
        if (StringUtils.hasText(user.getEmail())) {
            repository.findByEmailIgnoreCase(user.getEmail().toLowerCase())
                    .ifPresent(dbUser -> {
                        throw new IllegalRequestDataException("The user with email "
                                + dbUser.getEmail()
                                + " already exist with id "
                                + dbUser.getId());
                    });
        }
    }
}