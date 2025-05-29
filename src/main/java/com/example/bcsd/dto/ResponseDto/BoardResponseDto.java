package com.example.bcsd.dto.ResponseDto;

import com.example.bcsd.model.Board;


public record BoardResponseDto(
    Long id,
    String title
) {
    public static BoardResponseDto from(Board board) {
        return new BoardResponseDto(board.getId(), board.getTitle());
    }
}
