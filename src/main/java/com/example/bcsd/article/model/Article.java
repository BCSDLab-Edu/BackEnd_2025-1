package com.example.bcsd.article.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Long authorId;

    @NotNull
    @Column(nullable = false)
    private Long boardId;

    @Size(max = 255)
    @NotNull
    @Column(nullable = false)
    private String title;

    @NotNull
    @Column(nullable = false)
    private String content;

    private LocalDateTime createdDate;
    private LocalDateTime editedDate;

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateEditedDate(LocalDateTime editedDate) {
        this.editedDate = editedDate;
    }
}
