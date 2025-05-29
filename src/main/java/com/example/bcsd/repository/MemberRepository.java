package com.example.bcsd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.bcsd.model.Member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Member> findById(Long id) {
        Member member = entityManager.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public List<Member> findAll() {
        String jpql = "SELECT m FROM Member m";
        return entityManager.createQuery(jpql, Member.class).getResultList();
    }

    public void deleteById(Long id) {
        Member member = entityManager.find(Member.class, id);
        if (member != null) {
            entityManager.remove(member);
        }
    }

    public void save(Member member) {
        if (member.getId() == null) {
            entityManager.persist(member);
        } else {
            entityManager.merge(member);
        }
    }
}
