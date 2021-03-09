package com.mooc.moocServer.repository;

import com.mooc.moocServer.dto.OrganizationDto;
import com.mooc.moocServer.entity.Organization;
import com.mooc.moocServer.mapper.OrganizationMapper;
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

    public List<Organization> findAll() {
        return em.createQuery("select o from Organization o", Organization.class).getResultList();

    }

    public List<Organization> findById(String id) {
        return em.createQuery("select o from Organization o where o.id = :id", Organization.class).setParameter("id", id).getResultList();
    }
}
