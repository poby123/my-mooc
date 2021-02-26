package com.mooc.moocServer.service;

import com.mooc.moocServer.domain.Board;
import com.mooc.moocServer.domain.Category;
import com.mooc.moocServer.domain.Comment;
import com.mooc.moocServer.domain.Member;
import com.mooc.moocServer.repository.BoardRepository;
import com.mooc.moocServer.repository.CategoryRepository;
import com.mooc.moocServer.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void addBoard(String memberId, Long categoryId, String content) {
        Member memberOne = memberRepository.findOne(memberId);
        Category categoryOne = categoryRepository.findOne(categoryId);
        Board board = Board.createBoard(memberOne, categoryOne, content);

        boardRepository.save(board);
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }


    public Board getBoard(Long id) {
        return boardRepository.findOne(id);
    }

    public List<Comment> getComments(Long boardId){
        Board board = boardRepository.findOne(boardId);
        return board.getComments();
    }
}
