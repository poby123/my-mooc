package com.mooc.moocServer.service;

import com.mooc.moocServer.dto.OrganizationDto;
import com.mooc.moocServer.entity.Organization;
import com.mooc.moocServer.mapper.OrganizationMapper;
import com.mooc.moocServer.repository.OrganizationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper om = new OrganizationMapper();

    @Transactional
    public OrganizationDto.Response addOrganization(String requestId) {
        Organization organization = new Organization(requestId);
        validateDuplicateOrganization(organization);
        organizationRepository.save(organization);

        return om.organizationToResponseDto(organization);
    }

    public List<OrganizationDto.Response> getAllOrganizations() {
        return om.organizationListToResponseDtoList(organizationRepository.findAll());
    }

    public OrganizationDto.Response getOrganization(@NonNull String organizationId) {
        Organization o = organizationRepository.findOne(organizationId);
        return om.organizationToResponseDto(o);
    }

    // utility
    private void validateDuplicateOrganization(Organization organization) {
        List<Organization> organizationList = organizationRepository.findById(organization.getId());
        if (!organizationList.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 조직입니다.");
        }
    }
}
