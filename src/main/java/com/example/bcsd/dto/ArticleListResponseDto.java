package com.example.bcsd.dto;

import java.util.List;

public class ArticleListResponseDto {
    private String boardName;
    private List<ArticleResponseDto> articleResponseDtos;

    public ArticleListResponseDto(String boardName, List<ArticleResponseDto> articleResponseDtos) {
        this.boardName = boardName;
        this.articleResponseDtos = articleResponseDtos;
    }

    public List<ArticleResponseDto> getArticleResponseDtos() {
        return articleResponseDtos;
    }

    public String getBoardName() {
        return boardName;
    }
}
