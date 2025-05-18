package com.example.bcsd;

public class User {
    private String name;
    private int age;


    //생성자
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //getter
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

