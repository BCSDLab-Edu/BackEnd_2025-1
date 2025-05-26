package com.example.bcsd.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionStatus {
    ARTICLE_NOT_EXIST(404, "존재하지 않는 post 입니다."),
    BOARD_NOT_EXIST(404, "존재하지 않는 board 입니다."),
    MEMBER_NOT_EXIST(404, "존재하지 않는 member 입니다."),

    EMAIL_IS_EXIST(409, "이미 등록된 email 입니다."),

    ARTICLE_MEMBER_NOT_EXIST(400, "해당 게시물의 사용자가 존재하지 않습니다."),
    ARTICLE_BOARD_NOT_EXIST(400, "헤당 게시물의 게시판이 존재하지 않습니다."),

    MEMBER_HAS_NULL(400, "member에서 작성하지 않은 항목이 존재합니다."),
    ARTICLE_HAS_NULL(400, "post에서 작성하지 않은 항목이 존재합니다."),
    BOARD_HAS_NULL(400, "board에서 작성하지 않은 항목이 존재합니다."),

    ARTICLE_WRITTEN_BY_MEMBER_IS_EXIST(400, "해당 member가 작성한 post가 존재합니다."),
    ARTICLE_EXISTS_ON_BOARD(400, "해당 board에 post가 존재합니다.");

    private final int statusCode;
    private final String message;
}
