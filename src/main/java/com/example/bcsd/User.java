package com.example.bcsd;

import com.fasterxml.jackson.annotation.JsonView;

public class User {

    public interface WithoutAgeView {};
    public interface WithAgeView extends WithoutAgeView {};

    private int age;
    private String name;


    public User() {
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @JsonView(WithAgeView.class)
    public int getAge() {
        return this.age;
    }

    @JsonView(WithoutAgeView.class)
    public String getUsername() {
        return this.name;
    }
}