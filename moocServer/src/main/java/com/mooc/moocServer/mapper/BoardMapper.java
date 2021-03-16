package com.mooc.moocServer.mapper;

import com.mooc.moocServer.dto.BoardDto;
import com.mooc.moocServer.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardMapper {

    private final MemberMapper memberMapper;

    // Board -> Dto.Response
    public BoardDto.Response boardToBoardResponse(Board board){
        BoardDto.Response res = new BoardDto.Response();

        res.setId(board.getId());
        res.setContent(board.getContent());
        res.setGood(board.getGood());
        res.setWriter(memberMapper.memberToMemberSimpleResponse(board.getWriter()));
        res.setCommentList(board.getComments());

        return res;
    }

    // List<Board> -> List<Dto.Response>
    public List<BoardDto.Response> boardToBoardResponseList(List<Board> boards) {
        List<BoardDto.Response> res = new ArrayList<>(boards.size());

        for(Board board : boards){
            res.add(boardToBoardResponse(board));
        }

        return res;
    }
}
