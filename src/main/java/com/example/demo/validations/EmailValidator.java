package com.example.demo.validations;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
//        TODO  Need to write Regex to Validate Email
        return true;
    }
}
