package com.example.bcsd.repository;

import com.example.bcsd.domain.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    @PersistenceContext
    private EntityManager em;

    public ArticleRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    public List<Article> findAll() {
        return em.createQuery("select a from Article a", Article.class)
                .getResultList();
    }

    @Override
    public List<Article> findByBoardId(Long boardId) {
        return em.createQuery("SELECT a FROM Article a WHERE a.boardId = :boardId", Article.class)
                .setParameter("boardId", boardId)
                .getResultList();
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(em.find(Article.class, id));
    }

    @Override
    public boolean existsByWriterId(Long writerId) {
        Long count = em.createQuery("SELECT COUNT(a) FROM Article a WHERE a.writerId = :writerId", Long.class)
                .setParameter("writerId", writerId)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsByBoardId(Long boardId) {
        Long count = em.createQuery("SELECT COUNT(a) FROM Article a WHERE a.boardId = :boardId", Long.class)
                .setParameter("boardId", boardId)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public Article save(Article article) {
        em.persist(article);
        return article;
    }

    @Override
    public void update(Article article) {
        em.merge(article);
    }

    @Override
    public boolean deleteById(Long id) {
        Article article = em.find(Article.class, id);
        if (article != null) {
            em.remove(article);
            return true;
        }
        return false;
    }
}
