package com.example.bcsd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.bcsd.model.Board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class BoardRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Board> findById(Long id) {
        Board board = entityManager.find(Board.class, id);
        return Optional.ofNullable(board);
    }

    public List<Board> findAll() {
        String jpql = "SELECT b FROM Board b";
        return entityManager.createQuery(jpql, Board.class).getResultList();
    }

    public void deleteById(Long id) {
        Board board = entityManager.find(Board.class, id);
        if (board != null) {
            entityManager.remove(board);
        }
    }

    public void save(Board board) {
        if (board.getId() == null) {
            entityManager.persist(board);
        } else {
            entityManager.merge(board);
        }
    }
}
