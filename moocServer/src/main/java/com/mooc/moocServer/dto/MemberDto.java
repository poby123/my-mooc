package com.mooc.moocServer.dto;

import com.mooc.moocServer.entity.Board;
import com.mooc.moocServer.entity.Comment;
import com.mooc.moocServer.entity.MemberRole;
import com.mooc.moocServer.entity.Organization;
import lombok.*;

import java.util.List;

public class MemberDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Info {
        private String id;
        private String password;
        private String image;
        private Organization organization;
        private List<String> roles;
        private List<Board> boards;
        private List<Comment> comments;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Request {
        private String id;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class SignupRequest {
        private String id;
        private String password;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class SignInRequest {
        private String id;
        private String password;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class SignInResponse{
        private String result;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Response {
        private String id;
        private String image;
        private Organization organization;
        private List<String> roles;
        private List<Board> boards;
        private List<Comment> comments;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class SimpleResponse {
        private String id;
        private String image;
        private List<String> roles;
    }

}
