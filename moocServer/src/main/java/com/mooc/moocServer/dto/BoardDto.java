package com.mooc.moocServer.dto;

import com.mooc.moocServer.entity.Category;
import com.mooc.moocServer.entity.Comment;
import com.mooc.moocServer.entity.Member;
import lombok.*;

import java.util.List;

public class BoardDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Info {
        private Long id;
        private Member writer;
        private Category category;
        private String content;
        private Long good;
        private List<Comment> commentList;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Request {
        private Long id;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class AddRequest {
        private String writerId;
        private Long categoryId;
        private String content;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Response {
        private Long id;
        private MemberDto.SimpleResponse writer;
        private String content;
        private Long good;
        private List<Comment> commentList;
    }


}
