package com.example.bcsd.repository;

import com.example.bcsd.domain.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDao {
    @PersistenceContext
    private EntityManager em;

    public void insert(Board board) {
        em.persist(board);
    }

    public List<Board> findAll() {
        return em.createQuery("SELECT b FROM Board b", Board.class).getResultList();
    }

    public Board findById(Long id) {
        return em.find(Board.class, id);
    }

    public void update(Long id, Board board) {
        Board found = em.find(Board.class, id);
        if (found != null) {
            found.setName(board.getName());
        }
    }

    public void delete(Long id) {
        Board board = em.find(Board.class, id);
        if (board != null) em.remove(board);
    }
}
