-- 회원 테이블 생성
CREATE TABLE member
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    email    VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- 게시판 테이블 생성
CREATE TABLE board
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- 게시글 테이블 생성
CREATE TABLE article
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    author_id     BIGINT       NOT NULL,
    board_id      BIGINT       NOT NULL,
    title         VARCHAR(255) NOT NULL,
    content       TEXT         NOT NULL,
    created_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

ALTER TABLE article
    ADD CONSTRAINT FK_article_author
        FOREIGN KEY (author_id) REFERENCES member (id);

ALTER TABLE article
    ADD CONSTRAINT FK_article_board
        FOREIGN KEY (board_id) REFERENCES board (id);
