package com.mooc.moocServer.repository;

import com.mooc.moocServer.entity.Category;
import com.mooc.moocServer.entity.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {
    private final EntityManager em;

    public Long save(Category category) {
        em.persist(category);
        return category.getId();
    }

    public Category findOne(Long id) {
        return em.find(Category.class, id);
    }

    public List<Category> findAll() {
        return em.createQuery("select c from Category c", Category.class).getResultList();
    }

    public List<Category> findByOrganization(Organization organization){
        return em.createQuery("select c from Category c where c.organization = :o", Category.class).setParameter("o", organization.getId()).getResultList();
    }
}
