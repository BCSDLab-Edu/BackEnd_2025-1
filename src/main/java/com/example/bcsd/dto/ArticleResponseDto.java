package com.example.bcsd.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ArticleResponseDto {
    private Long id;
    private String title;
    private String content;
    private String authorName;
    private String boardName;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
