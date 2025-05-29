package com.example.bcsd.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bcsd.dto.RequestDto.ArticleCreateRequestDto;
import com.example.bcsd.dto.RequestDto.ArticleUpdateRequestDto;
import com.example.bcsd.dto.ResponseDto.ArticleResponseDto;
import com.example.bcsd.service.ArticleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<ArticleResponseDto>> getArticles() {
        List<ArticleResponseDto> responseDtos = articleService.getArticles();
        return ResponseEntity.ok().body(responseDtos);
    }

    @GetMapping(params = "boardId")
    public ResponseEntity<List<ArticleResponseDto>> getArticlesByBoardId(@RequestParam Long boardId) {
        List<ArticleResponseDto> responseDtos = articleService.getArticlesByBoardId(boardId);
        return ResponseEntity.ok().body(responseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> getArticle(@PathVariable(name = "id") Long id) {
        ArticleResponseDto responseDto = articleService.getArticle(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping
    public ResponseEntity<ArticleResponseDto> createArticle(@RequestBody ArticleCreateRequestDto requestDto) {
        ArticleResponseDto responseDto = articleService.createArticle(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> updateArticle(@PathVariable(name = "id") Long id,
            @RequestBody ArticleUpdateRequestDto requestDto) {
        ArticleResponseDto responseDto = articleService.updateArticle(id,requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable(name = "id") Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

}
