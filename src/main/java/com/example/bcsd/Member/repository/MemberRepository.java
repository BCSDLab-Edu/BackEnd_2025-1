package com.example.bcsd.member.repository;

import com.example.bcsd.member.model.Member;
import com.example.bcsd.member.util.MemberUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Member save(Member member) {
        em.persist(member);

        return member;
    }

    public Member findById(Long id) {
        Member member = em.find(Member.class, id);

        return member;
    }

    public Optional<Member> findByEmail(String email) {
        String jpql = "SELECT m FROM member m WHERE m.email = :email";
        try {
            Member member = em.createQuery(jpql, Member.class).setParameter("email", email).getSingleResult();
            
            return Optional.ofNullable(member);
        } catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public boolean memberExists(Long id) {
        return em.find(Member.class, id) != null;
    }

    public List<Member> findAll() {
        String jpql = "SELECT m FROM member";

        TypedQuery<Member> typedQuery = em.createQuery(jpql, Member.class);

        return typedQuery.getResultList();
    }

    @Transactional
    public Member updateSave(Member member) {
        member.updatePassword(MemberUtil.Pass2Hash(member.getPassword()));

        if (member.getEmail() == null) {
            member.updateEmail(em.find(Member.class, member.getId()).getEmail());
        }

        return em.merge(member);
    }

    @Transactional
    public void delete(Long id) {
        Member member = em.find(Member.class, id);

        if (member != null) {
            em.remove(member);
        }
    }
}
