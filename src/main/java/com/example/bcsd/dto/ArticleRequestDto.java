package com.example.bcsd.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleRequestDto {
    private Long authorId;
    private Long boardId;
    private String title;
    private String content;
}
