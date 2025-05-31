package com.example.bcsd.repository;

import com.example.bcsd.domain.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepositoryImpl implements BoardRepository {

    @PersistenceContext
    private EntityManager em;

    public BoardRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Board> findAll() {
        return em.createQuery("SELECT b FROM Board b", Board.class)
                .getResultList();
    }

    @Override
    public Optional<Board> findById(Long id) {
        return Optional.ofNullable(em.find(Board.class, id));
    }

    @Override
    public Optional<String> findNameById(Long id) {
        String jpql = "SELECT b.name FROM Board b WHERE b.id = :id";
        List<String> results = em.createQuery(jpql, String.class)
                .setParameter("id", id)
                .getResultList();
        return results.stream().findFirst();
    }

    @Override
    public Board save(Board board) {
        em.persist(board);
        return board;
    }

    @Override
    public boolean deleteById(Long id) {
        Board board = em.find(Board.class, id);
        if (board != null) {
            em.remove(board);
            return true;
        }
        return false;
    }
}
