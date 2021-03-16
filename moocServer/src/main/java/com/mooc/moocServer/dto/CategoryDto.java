package com.mooc.moocServer.dto;

import com.mooc.moocServer.entity.Board;
import lombok.*;

import java.util.List;

public class CategoryDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Info {
        private Long id;
        private String name;
        private List<Board> boards;
        private String organizationId;
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
    public static class AddRequest{
        private String name;
        private String organizationId;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class Response {
        private Long id;
        private String name;
        private List<BoardDto.Response> boards;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class SimpleResponse {
        private Long id;
        private String name;
    }

}
