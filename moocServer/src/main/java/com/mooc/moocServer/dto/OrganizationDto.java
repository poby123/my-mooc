package com.mooc.moocServer.dto;

import com.mooc.moocServer.entity.Category;
import com.mooc.moocServer.entity.Member;
import com.mooc.moocServer.entity.Organization;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class OrganizationDto {
    private String id;
    private List<Category> categories;
    private List<MemberDto> members;

    public OrganizationDto(@NonNull String id) {
        this.id = id;
    }

    public static OrganizationDto entityToDto(@NonNull Organization organization) {
        OrganizationDto dto = new OrganizationDto();
        dto.id = organization.getId();
        dto.categories = organization.getCategories();
        dto.members = new ArrayList<>();

        List<Member> members = organization.getMembers();
        for(Member m : members){
            dto.members.add(MemberDto.entityToDto(m));
        }

        return dto;
    }
}
