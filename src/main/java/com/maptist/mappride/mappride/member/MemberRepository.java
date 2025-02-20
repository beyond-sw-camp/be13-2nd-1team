package com.maptist.mappride.mappride.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Optional<Member> findByEmail(String email) {
        try {
            return Optional.ofNullable(
                em.createQuery("SELECT m FROM Member m WHERE m.email = :email", Member.class)
                  .setParameter("email", email)
                  .getSingleResult()
            );
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

}
