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
    public BoardDto.Response addBoard(@NonNull String memberId, @NonNull Long categoryId, @NonNull String content) throws NullPointerException{
        Member member = memberRepository.findOne(memberId);
        Category category = categoryRepository.findOne(categoryId);

        if(member == null){
            throw new NullPointerException("[보드 추가시] 해당 멤버가 존재하지 않습니다.");
        }
        if(category == null){
            throw new NullPointerException("[보드 추가시] 해당 카테고리가 존재하지 않습니다.");
        }

        Board board = Board.createBoard(member, category, content);

        member.addBoard(board);
        category.addBoard(board);
        boardRepository.save(board);

        return boardMapper.boardToBoardResponse(board);
    }

    public List<BoardDto.Response> getBoards(@NonNull Long categoryId) throws NullPointerException{
        // 카테고리 조회
        Category category = categoryRepository.findOne(categoryId);
        if(category == null){
            throw new NullPointerException("해당 카테고리가 존재하지 않습니다.");
        }

        List<Board> boards = category.getBoards();
        return boardMapper.boardToBoardResponseList(boards);
    }

    public BoardDto.Response getBoard(@NonNull Long id) throws NullPointerException{
        Board board = boardRepository.findOne(id);
        if(board == null){
            throw new NullPointerException("존재하지 않는 글입니다.");
        }

        return boardMapper.boardToBoardResponse(board);
    }
}
