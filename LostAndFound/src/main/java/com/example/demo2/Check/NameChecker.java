package com.example.demo2.Check;

public class NameChecker implements Checker{
    @Override
    public boolean isValid(String text) {
        return (text.matches("^[a-zA-Z]+$") && !text.isEmpty());
    }
}
