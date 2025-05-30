package com.example.bcsd.repository;


import com.example.bcsd.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    List<Board> findAll();
    Optional<Board> findById(Long id);
    Optional<String> fineNameById(Long id);
    Board save(Board board);
    boolean deleteById(Long id);
}