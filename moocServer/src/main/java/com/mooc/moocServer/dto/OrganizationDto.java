package com.mooc.moocServer.dto;

import com.mooc.moocServer.entity.Category;
import com.mooc.moocServer.entity.Member;
import com.mooc.moocServer.entity.Organization;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class OrganizationDto {
    private String id;
    private List<Category> categories;
    private List<Member> members;

    @Builder
    public OrganizationDto(@NonNull String id){
        this.id = id;
    }

    public static OrganizationDto entityToDto(Organization organization){
        OrganizationDto dto = new OrganizationDto();
        dto.id = organization.getId();
        dto.categories = organization.getCategories();
        dto.members = organization.getMembers();

        return dto;
    }
}
