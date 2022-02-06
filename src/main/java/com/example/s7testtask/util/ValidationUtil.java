package com.example.s7testtask.util;

import com.example.s7testtask.error.IllegalRequestDataException;
import com.example.s7testtask.model.User;

public class ValidationUtil {

    public static void checkNew(User bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }
}