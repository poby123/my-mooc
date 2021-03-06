package com.mooc.moocServer.mapper;

import com.mooc.moocServer.dto.CategoryDto;
import com.mooc.moocServer.dto.MemberDto;
import com.mooc.moocServer.dto.OrganizationDto;
import com.mooc.moocServer.entity.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrganizationMapper {

    private final MemberMapper memberMapper;
    private final CategoryMapper categoryMapper;

    // Organization -> Dto.Info
    public OrganizationDto.Info organizationToInfoDto(Organization o) {
        return new OrganizationDto.Info(o.getId(), o.getCategories(), o.getMembers());
    }

    // Organization -> Dto.Response
    public OrganizationDto.Response organizationToResponseDto(Organization o) {
        List<MemberDto.SimpleResponse> members = memberMapper.memberListToMemberSimpleResponseList(o.getMembers());
        List<CategoryDto.SimpleResponse> simpleCategories = categoryMapper.categoryListToCategorySimpleResponseList(o.getCategories());
        return new OrganizationDto.Response(o.getId(), simpleCategories, members);
    }

    // List<Organization> -> List<Dto.Info>
    public List<OrganizationDto.Info> organizationListToInfoDtoList(List<Organization> organizations) {
        List<OrganizationDto.Info> ret = new ArrayList<>(organizations.size());

        for (Organization o : organizations) {
            ret.add(organizationToInfoDto(o));
        }

        return ret;
    }

    // List<Organization> -> List<Dto.Response>
    public List<OrganizationDto.Response> organizationListToResponseDtoList(List<Organization> organizations) {
        List<OrganizationDto.Response> ret = new ArrayList<>(organizations.size());

        for (Organization o : organizations) {
            ret.add(organizationToResponseDto(o));
        }

        return ret;
    }
}
