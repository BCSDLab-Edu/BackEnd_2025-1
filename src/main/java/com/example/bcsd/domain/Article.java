package com.example.bcsd.domain;

import java.time.LocalDateTime;

public class Article {
    private Long id;
    private Long authorId;
    private Long boardId;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
