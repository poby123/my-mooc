package com.mooc.moocServer.controller;

import com.mooc.moocServer.dto.CommentDto;
import com.mooc.moocServer.entity.Board;
import com.mooc.moocServer.entity.Comment;
import com.mooc.moocServer.service.BoardService;
import com.mooc.moocServer.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/comment")
    public ResponseEntity<?> getComment(@RequestParam("board") Long boardId){
        Board board = boardService.getBoard(boardId);
        List<Comment> comments = board.getComments();
        List<CommentDto> commentDtos = new ArrayList<>(comments.size());

        for(Comment c : comments){
            commentDtos.add(CommentDto.entityToDto(c));
        }

        return ResponseEntity.status(HttpStatus.OK).body(commentDtos);
    }

    @PostMapping("/comment")
    public ResponseEntity<?> addComment(@RequestBody CommentDto commentDto){
        Long commentId = commentService.addComment(commentDto.getWriterId(), commentDto.getBoardId(), commentDto.getContent());
        Comment comment = commentService.getComment(commentId);
        CommentDto dto = CommentDto.entityToDto(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
