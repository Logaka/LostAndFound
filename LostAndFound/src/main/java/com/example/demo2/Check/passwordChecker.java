package com.example.demo2.Check;

public class passwordChecker implements Checker{
    @Override
    public boolean isValid(String text) {
        System.out.println("pass"+(text.matches("^(?=.*[a-zA-Z])(?=.*\\d).*$") && !text.isEmpty()));
        return (text.matches("^(?=.*[a-zA-Z])(?=.*\\d).*$") && !text.isEmpty() && text.length()>=8);
    }
}
