package com.example.s7testtask.util;

import com.example.s7testtask.error.IllegalRequestDataException;
import com.example.s7testtask.model.Status;
import org.springframework.core.convert.converter.Converter;

public class StringToStatusConverter implements Converter<String, Status> {
    @Override
    public Status convert(String source) {
        Status status;
        try {
            status = Status.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalRequestDataException("Unsuitable parameter for Status type, use 'OPEN' or 'TAKEN'");
        }
        return status;
    }
}
