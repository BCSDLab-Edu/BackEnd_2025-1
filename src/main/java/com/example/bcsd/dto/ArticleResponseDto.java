package com.example.bcsd.dto;

import java.time.LocalDateTime;

public record ArticleResponseDto(
        Long id,
        Long authorId,
        Long boardId,
        String title,
        String content,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
) {
}
