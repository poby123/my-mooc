package com.mooc.moocServer.service;

import com.mooc.moocServer.dto.BoardDto;
import com.mooc.moocServer.entity.Board;
import com.mooc.moocServer.entity.Category;
import com.mooc.moocServer.entity.Member;
import com.mooc.moocServer.mapper.BoardMapper;
import com.mooc.moocServer.repository.BoardRepository;
import com.mooc.moocServer.repository.CategoryRepository;
import com.mooc.moocServer.repository.MemberRepository;
import lombok.NonNull;
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
    private final BoardMapper boardMapper;

    @Transactional
    public BoardDto.Response addBoard(String memberId, Long categoryId, String content) {
        Member member = memberRepository.findOne(memberId);
        Category category = categoryRepository.findOne(categoryId);
        Board board = Board.createBoard(member, category, content);

        member.addBoard(board);
        category.addBoard(board);
        boardRepository.save(board);

        return boardMapper.boardToBoardResponse(board);
    }

    public List<BoardDto.Response> getBoards(@NonNull Long categoryId){
        Category category = categoryRepository.findOne(categoryId);
        List<Board> boards = category.getBoards();

        return boardMapper.boardToBoardResponseList(boards);
    }

    public BoardDto.Response getBoard(Long id) {
        Board board = boardRepository.findOne(id);
        return boardMapper.boardToBoardResponse(board);
    }
}
