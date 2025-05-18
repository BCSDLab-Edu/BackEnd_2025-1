package com.example.bcsd.entity;

public class Member {

    private Integer id;
    private String name;
    private String email;
    private String password;

    public Member(
        Integer id,
        String name,
        String email,
        String password
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
