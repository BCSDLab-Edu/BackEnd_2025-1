package com.example.bcsd.repository;

import com.example.hellospring.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepository {
    private final List<Board> boards = new ArrayList<>();

    public List<Board> findAll() {
        return boards;
    }

    public Optional<Board> findById(Long id) {
        return boards.stream().filter(b -> b.getId().equals(id)).findFirst();
    }

    public Board save(Board board) {
        boards.add(board);
        return board;
    }

    public boolean deleteById(Long id) {
        return boards.removeIf(b -> b.getId().equals(id));
    }
}