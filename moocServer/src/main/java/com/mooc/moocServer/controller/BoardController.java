package com.mooc.moocServer.controller;

import com.mooc.moocServer.dto.BoardDto;
import com.mooc.moocServer.entity.Board;
import com.mooc.moocServer.entity.Category;
import com.mooc.moocServer.service.BoardService;
import com.mooc.moocServer.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final CategoryService categoryService;

    @GetMapping("/board/{boardId}")
    public BoardDto getBoard(@PathVariable("boardId") Long boardId) {
        Board board = boardService.getBoard(boardId);
        return BoardDto.entityToDto(board);
    }

    @GetMapping("/board")
    public List<BoardDto> getBoards(@RequestParam("category") Long categoryId) {
        Category category = categoryService.getCategory(categoryId);
        List<Board> boards = category.getBoards();
        List<BoardDto> boardDto = new ArrayList<>(boards.size());
        for (Board board : boards) {
            boardDto.add(BoardDto.entityToDto(board));
        }
        return boardDto;
    }

    @PostMapping("/board")
    public ResponseEntity<?> addBoard(@RequestBody BoardDto boardDto) {
        Long boardId = boardService.addBoard(boardDto.getWriterId(), boardDto.getCategoryId(), boardDto.getContent());
        Board board = boardService.getBoard(boardId);
        //return BoardDto.entityToDto(board);
        return ResponseEntity.status(HttpStatus.CREATED).body(BoardDto.entityToDto(board));
    }

//    @DeleteMapping("/board/{boardId}")
//    public BoardDto deleteBoard(@PathVariable("boardId") Long boardId){
//
//    }
}
