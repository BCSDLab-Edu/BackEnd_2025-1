package com.example.bcsd.dto.RequestDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleCreateRequestDto {

    @JsonProperty("board_id")
    private Long boardId;

    @JsonProperty("author_id")
    private Long authorId;

    private String title;
    private String content;

}
