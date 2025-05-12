package com.example.bcsd.board.dto;

import com.example.bcsd.board.model.Board;

public record UpdateBoardResponse(
        String name
) {
    public static UpdateBoardResponse from(Board board) {
        return new UpdateBoardResponse(board.getName());
    }
}
