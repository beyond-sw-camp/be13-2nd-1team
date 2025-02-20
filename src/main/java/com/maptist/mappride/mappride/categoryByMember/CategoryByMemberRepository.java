package com.maptist.mappride.mappride.categoryByMember;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryByMemberRepository {

    private final EntityManager em;

    public void save(CategoryByMember categoryByMember) {
        em.persist(categoryByMember);
    }
}
