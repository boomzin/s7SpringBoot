package com.example.s7testtask.util;

import com.example.s7testtask.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidationUtil {

    public static void checkNew(User bean) {
        if (!bean.isNew()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}