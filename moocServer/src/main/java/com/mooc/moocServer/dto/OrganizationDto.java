package com.mooc.moocServer.dto;

import com.mooc.moocServer.entity.Category;
import com.mooc.moocServer.entity.Member;
import lombok.*;

import java.util.List;

public class OrganizationDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Info {
        private String id;
        private List<Category> categories;
        private List<Member> members;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Response {
        private String id;
        private List<CategoryDto.SimpleResponse> categories;
        private List<MemberDto.SimpleResponse> members;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Request {
        private String id;
    }

}
