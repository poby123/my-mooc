package com.mooc.moocServer.dto;

import com.mooc.moocServer.entity.Board;
import com.mooc.moocServer.entity.Comment;
import com.mooc.moocServer.entity.Member;
import lombok.*;

public class CommentDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Info {
        private Long id;
        private Member writer;
        private Long boardId;
        private String content;
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
        private Long boardId;
        private String content;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Response {
        private Info info;
        private String returnCode;
        private String returnMessage;
    }
}
