package com.example.bcsd.board.dto;

import com.example.bcsd.board.model.Board;

import jakarta.validation.constraints.NotNull;

public record CreateBoardRequest(
        @NotNull String name
) {
    public Board toBoard() {
        Board board = new Board();
        board.setName(name);

        return board;
    }
}