package com.example.bcsd.board.dto;

import java.util.List;

import com.example.bcsd.board.model.Board;

public record GetBoardsResponse(
        List<Board> boards,
        Integer boardCount
) {
}