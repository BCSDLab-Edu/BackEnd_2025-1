package com.example.bcsd.model;

public class Board {
    private Long id;
    private String boardName;

    public Board(Long id, String boardName){
        this.id = id;
        this.boardName = boardName;
    }

    public Long getId() {
        return id;
    }

    public String getBoardName() {
        return boardName;
    }
}
