package com.mooc.moocServer.service;

import com.mooc.moocServer.dto.CommentDto;
import com.mooc.moocServer.entity.Board;
import com.mooc.moocServer.entity.Comment;
import com.mooc.moocServer.entity.Member;
import com.mooc.moocServer.mapper.CommentMapper;
import com.mooc.moocServer.repository.BoardRepository;
import com.mooc.moocServer.repository.CommentRepository;
import com.mooc.moocServer.repository.MemberRepository;
import lombok.NonNull;
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
    public CommentDto.Response addComment(CommentDto.AddRequest request) throws NullPointerException{
        Member writer = memberRepository.findOne(request.getWriterId());
        if(writer == null){
            throw new NullPointerException("해당 멤버가 존재하지 않습니다.");
        }

        Board board = boardRepository.findOne(request.getBoardId());
        if(board == null){
            throw new NullPointerException("해당 게시물이 존재하지 않습니다.");
        }

        Comment comment = Comment.createComment(writer, board, request.getContent());

        writer.addComment(comment);
        board.addComment(comment);
        commentRepository.save(comment);

        return commentMapper.commentToCommentResponse(comment);
    }

    public CommentDto.Response getComment(@NonNull Long id) throws NullPointerException{
        Comment comment = commentRepository.findOne(id);
        if(comment == null){
            throw new NullPointerException("찾으시는 댓글이 존재하지 않습니다.");
        }

        return commentMapper.commentToCommentResponse(comment);
    }

    public List<CommentDto.Response> getComments(@NonNull Long boardId) throws NullPointerException{
        Board board = boardRepository.findOne(boardId);
        if(board == null){
            throw new NullPointerException("해당 게시물이 존재하지 않습니다.");
        }

        List<Comment> comments = board.getComments();
        return commentMapper.commentListToResponseDtoList(comments);
    }
}
