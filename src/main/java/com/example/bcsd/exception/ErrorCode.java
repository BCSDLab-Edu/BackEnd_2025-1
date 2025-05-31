package com.example.bcsd.exception;

public enum ErrorCode {
    CANNOT_FIND_ARTICLE(404, "해당 게시글을 찾을 수 없습니다."),
    CANNOT_FIND_BOARD(404, "해당 게시판을 찾을 수 없습니다."),
    CANNOT_FIND_MEMBER(404, "해당 회원을 찾을 수 없습니다."),
    EMAIL_ALREADY_EXISTS(409, "이미 해당 이메일로 가입한 회원이 존재합니다."),
    MEMBER_DOESNT_EXISTS(400, "해당 회원은 존재하지 않습니다."),
    BOARD_DOESNT_EXISTS(400, "해당 게시판은 존재하지 않습니다."),
    NULL_VALUE_EXISTS(400, "입력한 정보 중 비어있는 정보가 있습니다."),
    MEMBER_HAS_ARTICLES(400, "해당 사용자가 작성한 게시글이 있어 삭제할 수 없습니다."),
    BOARD_HAS_ARTICLES(400, "해당 게시판에 작성된 게시글이 있어 삭제할 수 없습니다.");

    private final int code;
    private final String message;


    ErrorCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}