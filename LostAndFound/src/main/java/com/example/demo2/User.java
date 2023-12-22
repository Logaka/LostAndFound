package com.example.demo2;

public class User {

    private static  User user;
    private String name;
    private String lastName;
    private int password;
    private String email;
    private String path;
    private User() {

    }

    public static User getUser(){
        if(user == null){
           return user = new User();
        }
        return user;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
