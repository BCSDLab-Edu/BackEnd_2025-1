package com.example.bcsd.board.dto;

import com.example.bcsd.article.dto.GetArticlesResponse;
import com.example.bcsd.board.model.Board;

public record BoardWithArticles(
        Board board,
        GetArticlesResponse articles
) {

}
