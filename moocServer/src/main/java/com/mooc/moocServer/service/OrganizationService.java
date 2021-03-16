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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    @Transactional
    public OrganizationDto.Response addOrganization(String requestId) {
        Organization organization = new Organization(requestId);
        validateDuplicateOrganization(organization);
        organizationRepository.save(organization);

        return organizationMapper.organizationToResponseDto(organization);
    }

    public List<OrganizationDto.Response> getAllOrganizations() {
        List<Organization> all = organizationRepository.findAll();
        return organizationMapper.organizationListToResponseDtoList(all);
    }

    public OrganizationDto.Response getOrganization(@NonNull String organizationId) throws NullPointerException {
        Optional<Organization> organizationOptional = organizationRepository.findById(organizationId);
        Organization organization = organizationOptional.orElseThrow(() ->
                new NullPointerException("찾으시는 " + organizationId + "가 존재하지 않습니다.")
        );

        return organizationMapper.organizationToResponseDto(organizationOptional.get());
    }

    // utility
    private void validateDuplicateOrganization(Organization organization) {
        Optional<Organization> organizationOptional = organizationRepository.findById(organization.getId());
        if (organizationOptional.isPresent()) {
            throw new IllegalStateException("이미 존재하는 조직입니다.");
        }
    }
}
