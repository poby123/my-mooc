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
    public Long addBoard(String memberId, Long categoryId, String content) {
        Member member = memberRepository.findOne(memberId);
        Category category = categoryRepository.findOne(categoryId);
        Board board = Board.createBoard(member, category, content);

        member.addBoard(board);
        category.addBoard(board);
        boardRepository.save(board);

        return board.getId();
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }


    public Board getBoard(Long id) {
        return boardRepository.findOne(id);
    }
}
