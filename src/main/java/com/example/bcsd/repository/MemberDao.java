package com.example.bcsd.repository;

import com.example.bcsd.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDao {
    @PersistenceContext
    private EntityManager em;

    public void insert(Member member) {
        em.persist(member);
    }

    public List<Member> findAll() {
        return em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public void update(Long id, Member member) {
        Member found = em.find(Member.class, id);
        if (found != null) {
            found.setName(member.getName());
            found.setEmail(member.getEmail());
            found.setPassword(member.getPassword());
        }
    }

    public void delete(Long id) {
        Member member = em.find(Member.class, id);
        if (member != null) em.remove(member);
    }
}
