package com.mooc.moocServer.controller;

import com.mooc.moocServer.dto.CommentDto;
import com.mooc.moocServer.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/comment")
    public ResponseEntity<?> getComment(@RequestParam("board") Long boardId) {
        List<CommentDto.Response> res = commentService.getComments(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/comment")
    public ResponseEntity<?> addComment(@RequestBody CommentDto.AddRequest commentDto) {
        CommentDto.Response res = commentService.addComment(commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}
