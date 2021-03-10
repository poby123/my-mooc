package com.mooc.moocServer.mapper;

import com.mooc.moocServer.dto.CommentDto;
import com.mooc.moocServer.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final MemberMapper memberMapper;

    // Comment -> Dto.Response
    public CommentDto.Response commentToCommentResponse(Comment comment){
        CommentDto.Response res = new CommentDto.Response();

        res.setId(comment.getId());
        res.setWriter(memberMapper.memberToMemberSimpleResponse(comment.getWriter()));
        res.setContent(comment.getContent());
        res.setBoardId(comment.getBoard().getId());

        return res;
    }

    // List<Comment> -> List<CommentDto.Response>
    public List<CommentDto.Response> commentListToResponseDtoList(List<Comment> list){
        List<CommentDto.Response> ret = new ArrayList<>(list.size());

        for(Comment c : list){
            ret.add(commentToCommentResponse(c));
        }

        return ret;
    }
}
