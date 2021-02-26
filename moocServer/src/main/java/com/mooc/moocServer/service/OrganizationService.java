package com.mooc.moocServer.service;

import com.mooc.moocServer.domain.Category;
import com.mooc.moocServer.domain.Organization;
import com.mooc.moocServer.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    @Transactional
    public String addOrganization(String organizationId) {
        Organization organization = new Organization(organizationId);
        validateDuplicateOrganization(organization);
        organizationRepository.save(organization);

        return organization.getId();
    }

    private void validateDuplicateOrganization(Organization organization) {
        List<Organization> organizationList = organizationRepository.findById(organization.getId());
        if (!organizationList.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 조직입니다.");
        }
    }

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    public Organization getOrganization(String id) {
        return organizationRepository.findOne(id);
    }

//    public List<Category> getCategories(String organizationId) {
//        Organization organization = organizationRepository.findOne(organizationId);
//        return organization.getCategories();
//    }

}
