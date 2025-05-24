package com.example.bcsd.dto;


import com.example.bcsd.domain.Board;

public class BoardResponseDto {

    Long id;
    String name;

    public BoardResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.name = board.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
