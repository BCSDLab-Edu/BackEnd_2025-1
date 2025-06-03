package com.example.bcsd.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bcsd.dto.RequestDto.ArticleCreateRequestDto;
import com.example.bcsd.dto.RequestDto.ArticleUpdateRequestDto;
import com.example.bcsd.dto.ResponseDto.ArticleResponseDto;
import com.example.bcsd.exception.InvalidReferenceException;
import com.example.bcsd.exception.NotFoundException;
import com.example.bcsd.exception.NullRequestException;
import com.example.bcsd.model.Article;
import com.example.bcsd.model.Board;
import com.example.bcsd.model.Member;
import com.example.bcsd.repository.ArticleRepository;
import com.example.bcsd.repository.BoardRepository;
import com.example.bcsd.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {
 
    private final ArticleRepository articleRepository;
 
    private final MemberRepository memberRepository;
 
    private final BoardRepository boardRepository;

    public ArticleResponseDto getArticle(Long id) {
        Article article = articleRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NotFoundException("해당 기사가 없습니다."));
        return ArticleResponseDto.from(article);
    }

    public List<ArticleResponseDto> getArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(ArticleResponseDto::from)
                .collect(Collectors.toList());
    }

    public List<ArticleResponseDto> getArticlesByBoardId(Long boardId) {
        List<Article> articles = articleRepository.findByBoardId(boardId);
        return articles.stream()
                .map(ArticleResponseDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public ArticleResponseDto createArticle(ArticleCreateRequestDto requestDto) {

        if(requestDto == null) {
            throw new NullRequestException("요청 값이 null입니다.");
        }
        if(requestDto.getAuthorId() == null || requestDto.getBoardId() == null || requestDto.getTitle() == null || requestDto.getContent() == null) {
            throw new NullRequestException("요청 값중에 null이 있습니다.");
        }
        
        Member member = memberRepository.findById(requestDto.getAuthorId())
                .orElseThrow(() -> new InvalidReferenceException("해당 회원이 없습니다."));
        Board board = boardRepository.findById(requestDto.getBoardId())
                .orElseThrow(() -> new InvalidReferenceException("해당 게시판이 없습니다."));

        Article article = Article.builder()
                .board(board)
                .author(member)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .build();
        articleRepository.save(article);
        return ArticleResponseDto.from(article);
    }

    @Transactional
    public ArticleResponseDto updateArticle(Long id,ArticleUpdateRequestDto requestDto) {
        if(requestDto == null) {
            throw new NullRequestException("요청 값이 null입니다.");
        }
        if(requestDto.getBoardId() == null || requestDto.getTitle() == null || requestDto.getContent() == null) {
            throw new NullRequestException("요청 값중에 null이 있습니다.");
        }
        Board board = boardRepository.findById(requestDto.getBoardId())
                .orElseThrow(() -> new InvalidReferenceException("해당 게시판이 없습니다."));
        Article article = articleRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new InvalidReferenceException("해당 기사가 없습니다."));
        article.setBoard(board);
        article.setTitle(requestDto.getTitle());
        article.setContent(requestDto.getContent());
        article.setModifiedDate(LocalDateTime.now());
        articleRepository.save(article);

        return ArticleResponseDto.from(article);
    }

    @Transactional
    public void deleteArticle(Long id) {
        Article article = articleRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NotFoundException("해당 기사가 없습니다."));
        
        articleRepository.deleteById(article.getId());
    }
}
