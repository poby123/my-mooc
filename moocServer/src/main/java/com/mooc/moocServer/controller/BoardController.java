package com.mooc.moocServer.controller;

import com.mooc.moocServer.dto.BoardDto;
import com.mooc.moocServer.entity.Member;
import com.mooc.moocServer.service.BoardService;
import com.mooc.moocServer.service.CategoryService;
import com.mooc.moocServer.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;
    private final CategoryService categoryService;
    private final FileService fileService;

    @GetMapping("/board/{boardId}")
    public ResponseEntity<?> getBoard(@PathVariable("boardId") Long boardId) {
        BoardDto.Response res = boardService.getBoard(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/board")
    public ResponseEntity<?> getBoards(@RequestParam("category") Long categoryId, Pageable pageable) {
        List<BoardDto.Response> res = boardService.getBoards(categoryId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/board")
    public ResponseEntity<?> addBoard(@AuthenticationPrincipal Member member, @RequestParam("file") MultipartFile[] files, BoardDto.AddRequest boardDto) {
        log.info("========== INFORMATION : ");
        log.info("Member : ", member.getId());
        log.info("Files : ", files.length);
        log.info("Board Content : ", boardDto.getContent());
        log.info("Board Category : ", boardDto.getCategoryId());
        BoardDto.Response res = boardService.addBoard(member.getId(), boardDto.getCategoryId(), boardDto.getContent(), files);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

}
