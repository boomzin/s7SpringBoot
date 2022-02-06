package com.example.s7testtask.config;

import com.example.s7testtask.util.StringToGenderConverter;
import com.example.s7testtask.util.StringToStatusConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToGenderConverter());
        registry.addConverter(new StringToStatusConverter());
    }
}
