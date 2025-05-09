package com.example.bcsd.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.bcsd.model.Board;

@Component
public class BoardRepository {
    private static Long DEFAULT_ID = 0L;
    private List<Board> boards = new ArrayList<>(); // 데이터베이스 대용

    public BoardRepository() { // 초기 데이터
        save(new Board("제목1"));
    }

    public Long getNextId() {
        return ++DEFAULT_ID;
    }

    public Optional<Board> findById(Long id) {
        return boards.stream()
                .filter(board -> board.getId().equals(id))
                .findFirst();
    }

    public List<Board> findAll() {
        return new ArrayList<>(boards); // 복제 리스트 반환(변경 방지)
    }

    public void deleteById(Long id) {
        boards.removeIf(board -> board.getId().equals(id));
    }

    public void save(Board board) { // 저장(수정)
        if (board.getId() == null) {
            board.setId(getNextId()); // 생성
        } else {// 수정
            deleteById(board.getId());
        }
        boards.add(board);
    }

}
