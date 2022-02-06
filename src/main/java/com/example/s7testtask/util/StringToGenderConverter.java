package com.example.s7testtask.util;

import com.example.s7testtask.error.IllegalRequestDataException;
import com.example.s7testtask.model.Gender;
import org.springframework.core.convert.converter.Converter;

public class StringToGenderConverter implements Converter<String, Gender> {
    @Override
    public Gender convert(String source) {
        Gender gender;
        try {
            gender = Gender.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalRequestDataException("Unsuitable parameter for Gender type, use 'MALE' or 'FEMALE'");
        }
        return gender;
    }
}
