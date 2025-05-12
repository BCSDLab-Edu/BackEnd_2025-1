package com.example.bcsd.model;

import java.time.LocalDateTime;

public class ArticleModel {

    private final Long id;
    private final Long memberId;      // 작성자 id
    private final Long boardId;       // 게시판 id
    private String title;       // 제목
    private String content;     // 내용
    private final LocalDateTime createdDate;  // 작성일자
    private LocalDateTime updatedDate;  // 수정일자 (수정 가능)

    public ArticleModel(Long id, Long memberId, Long boardId, String title, String content, LocalDateTime createdDate) {
        this.id = id;
        this.memberId = memberId;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.updatedDate = createdDate; // 수정일자는 작성일자와 동일하게 초기화
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void updateArticle(String title, String content, LocalDateTime updatedDate) {
        this.title = title;
        this.content = content;
        this.updatedDate = updatedDate;
    }
}
