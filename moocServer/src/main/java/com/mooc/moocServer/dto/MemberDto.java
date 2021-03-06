package com.mooc.moocServer.dto;

import com.mooc.moocServer.entity.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class MemberDto {
    private String id;
    private String password;
    private String image;
    private String organizationId;
    private MemberRole role;
    private List<Board> boards;
    private List<Comment> comments;

    public static MemberDto entityToDto(Member m) {
        MemberDto dto = new MemberDto();
        dto.id = m.getId();

        if (m.getOrganization() != null) {
            dto.organizationId = m.getOrganization().getId();
        }
        dto.image = m.getImage();
        dto.role = m.getRole();
        dto.boards = m.getBoards();
        dto.comments = m.getComments();

        return dto;
    }
}
