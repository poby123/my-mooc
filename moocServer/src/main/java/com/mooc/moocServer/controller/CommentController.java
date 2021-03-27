package com.mooc.moocServer.controller;

import com.mooc.moocServer.dto.CommentDto;
import com.mooc.moocServer.entity.Member;
import com.mooc.moocServer.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/comment")
    public ResponseEntity<?> getComments(@RequestParam("board") Long boardId, Pageable pageable) {
        List<CommentDto.Response> res = commentService.getComments(boardId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/comment")
    public ResponseEntity<?> addComment(@AuthenticationPrincipal Member member, @RequestBody CommentDto.AddRequest commentDto) {
        CommentDto.Response res = commentService.addComment(member.getId(), commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}
