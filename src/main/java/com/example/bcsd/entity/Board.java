package com.example.bcsd.entity;

public class Board {

    private Integer id;
    private String name;

    public Board(
        Integer id,
        String name
    ) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
