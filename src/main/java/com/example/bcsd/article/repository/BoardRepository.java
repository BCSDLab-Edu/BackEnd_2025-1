package com.example.bcsd.article.repository;

import com.example.bcsd.article.model.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class BoardRepository {
    private final Map<Long, Board> boardDB = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Board save(Board board) {
        Long id = idCounter.getAndIncrement();
        board.setId(id);
        boardDB.put(id, board);
        return board;
    }

    public Optional<Board> findById(Long id) {
        return Optional.ofNullable(boardDB.get(id));
    }

    public List<Board> findAll() {
        return new ArrayList<>(boardDB.values());
    }

    public Board updateSave(Board board) {
        boardDB.put(board.getId(), board);
        return board;
    }

    public void delete(Long id) {
        boardDB.remove(id);
    }
}
