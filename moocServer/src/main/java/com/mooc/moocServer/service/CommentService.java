package com.mooc.moocServer.service;

import com.mooc.moocServer.dto.CommentDto;
import com.mooc.moocServer.entity.Board;
import com.mooc.moocServer.entity.Comment;
import com.mooc.moocServer.entity.Member;
import com.mooc.moocServer.mapper.CommentMapper;
import com.mooc.moocServer.repository.BoardRepository;
import com.mooc.moocServer.repository.CommentRepository;
import com.mooc.moocServer.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentMapper commentMapper;

    @Transactional
    public CommentDto.Response addComment(CommentDto.AddRequest request) {
        Member writer = memberRepository.findOne(request.getWriterId());
        Board board = boardRepository.findOne(request.getBoardId());
        Comment comment = Comment.createComment(writer, board, request.getContent());

        writer.addComment(comment);
        board.addComment(comment);
        commentRepository.save(comment);

        return commentMapper.commentToCommentResponse(comment);
    }

    public CommentDto.Response getComment(Long id){
        Comment comment = commentRepository.findOne(id);
        return commentMapper.commentToCommentResponse(comment);
    }

    public List<CommentDto.Response> getComments(Long boardId) {
        Board board = boardRepository.findOne(boardId);
        List<Comment> comments = board.getComments();
        return commentMapper.commentListToResponseDtoList(comments);
    }
}
