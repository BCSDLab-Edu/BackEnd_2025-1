package com.example.bcsd.model;

import java.util.Objects;

public class Board {
    private Long id;
    private String name;

    public Board() {
    }

    public Board(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
} 