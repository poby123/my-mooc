package com.mooc.moocServer.repository;

import com.mooc.moocServer.domain.Category;
import com.mooc.moocServer.domain.Member;
import com.mooc.moocServer.domain.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrganizationRepository {

    public final EntityManager em;

    public void save(Organization organization) {
        em.persist(organization);
    }

    public Organization findOne(String id) {
        return em.find(Organization.class, id);
    }

    public void addCategory(String organizationId, Category category) {
        Organization organization = em.find(Organization.class, organizationId);
        organization.addCategory(category);
    }

    public void addMember(String organizationId, Member member) {
        Organization organization = em.find(Organization.class, organizationId);
        organization.addMember(member);
    }

    public List<Organization> findAll() {
        return em.createQuery("select o from Organization o", Organization.class).getResultList();
    }

    public List<Organization> findById(String id) {
        return em.createQuery("select o from Organization o where o.id = :id", Organization.class).setParameter("id", id).getResultList();
    }
}
