package com.example.bcsd.board.repository;

import com.example.bcsd.board.model.Board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class BoardRepository {
    @PersistenceContext
    private EntityManager em;

    public Board save(Board board) {
        em.persist(board);

        return board;
    }

    public Board findById(Long id) {
        Board board = em.find(Board.class, id);

        return board;
    }

    public boolean boardExists(Long id) {
        return em.find(Board.class, id) != null;
    }

    public List<Board> findAll() {
        String jpql = "SELECT b FROM board b";
        TypedQuery<Board> typedQuery = em.createQuery(jpql, Board.class);

        return typedQuery.getResultList();
    }

    public Board updateSave(Board board) {
        return em.merge(board);
    }

    public void delete(Long id) {
        Board board = em.find(Board.class, id);

        if (board != null) {
            em.remove(board);
        }
    }
}
