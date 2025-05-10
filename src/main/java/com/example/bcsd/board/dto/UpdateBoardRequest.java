package com.example.bcsd.board.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateBoardRequest(
    @NotNull String name
) {
    
}
