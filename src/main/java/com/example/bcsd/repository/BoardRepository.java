package com.example.bcsd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bcsd.model.Board;

public interface  BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findById(Long id);
    
    List<Board> findAll();
    
    void deleteById(Long id);
    
    Board save(Board board);
}

