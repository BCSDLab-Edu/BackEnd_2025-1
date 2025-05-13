package com.example.bcsd.service;

import com.example.hellospring.domain.Article;
import com.example.hellospring.domain.Board;
import com.example.hellospring.dto.ArticleListResponseDto;
import com.example.hellospring.dto.ArticleRequestDto;
import com.example.hellospring.dto.ArticleResponseDto;
import com.example.hellospring.repository.ArticleRepository;
import com.example.hellospring.repository.BoardRepository;
import com.example.hellospring.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public ArticleService(ArticleRepository articleRepository, BoardRepository boardRepository, MemberRepository memberRepository) {
        this.articleRepository = articleRepository;
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    public List<ArticleListResponseDto> getAllArticles() {
        Map<Long, List<Article>> grouped = articleRepository.findAll().stream()
                .collect(Collectors.groupingBy(Article::getBoardId));

        return grouped.entrySet().stream()
                .map(entry -> {
                    Long boardId = entry.getKey();
                    String boardName = boardRepository.findById(boardId)
                            .map(Board::getName)
                            .orElse("알 수 없음");
                    List<ArticleResponseDto> responses = entry.getValue().stream()
                            .map(ArticleResponseDto::new)
                            .collect(Collectors.toList());
                    return new ArticleListResponseDto(boardName, responses);
                })
                .collect(Collectors.toList());
    }

    public ArticleResponseDto getArticleById(Long id) {
        return articleRepository.findById(id)
                .map(ArticleResponseDto::new)
                .orElseThrow(() -> new NullPointerException(""));
    }

    public ArticleResponseDto createArticle(ArticleRequestDto dto) {
        if (!memberRepository.findById(dto.getWriterId()).isPresent()) {
            throw new IllegalArgumentException("해당 작성자를 찾을 수 없습니다.");
        }
        if (!boardRepository.findById(dto.getBoardId()).isPresent()) {
            throw new IllegalArgumentException("해당 게시판을 찾을 수 없습니다.");
        }

        Article article = new Article(
                dto.getId(),
                dto.getWriterId(),
                dto.getBoardId(),
                dto.getTitle(),
                dto.getContent(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        articleRepository.save(article);
        return new ArticleResponseDto(article);
    }

    public ArticleResponseDto updateArticle(Long id, ArticleRequestDto dto) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NullPointerException(""));

        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setModifiedDate(LocalDateTime.now());

        return new ArticleResponseDto(article);
    }

    public void deleteArticle(Long id) {
        if (!articleRepository.deleteById(id)) {
            throw new NullPointerException("");
        }
    }
}
