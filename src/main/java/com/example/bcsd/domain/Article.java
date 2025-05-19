package com.example.bcsd.domain;

import java.time.LocalDateTime;

public record Article(
        Long id,
        Long authorId,
        Long boardId,
        String title,
        String content,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
) {
}
