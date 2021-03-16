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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentMapper commentMapper;

    @Transactional
    public CommentDto.Response addComment(CommentDto.AddRequest request) throws NullPointerException {
        // 멤버 가져오기
        Optional<Member> memberOptional = memberRepository.findById(request.getWriterId());
        Member writer = memberOptional.orElseThrow(() -> new NullPointerException("해당 멤버가 존재하지 않습니다."));

        // 글 가져오기
        Optional<Board> boardOptional = boardRepository.findById(request.getBoardId());
        Board board = boardOptional.orElseThrow(() -> new NullPointerException("존재하지 않는 글입니다."));

        // 댓글 만들기
        Comment comment = Comment.createComment(writer, board, request.getContent());

        writer.addComment(comment);
        board.addComment(comment);
        commentRepository.save(comment);

        return commentMapper.commentToCommentResponse(comment);
    }

    public CommentDto.Response getComment(@NonNull Long id) throws NullPointerException {

        Optional<Comment> commentOptional = commentRepository.findById(id);
        Comment comment = commentOptional.orElseThrow(() -> new NullPointerException("찾으시는 댓글이 존재하지 않습니다."));

        return commentMapper.commentToCommentResponse(comment);
    }

    public List<CommentDto.Response> getComments(@NonNull Long boardId, Pageable pageable) throws NullPointerException {
        List<Comment> comments = commentRepository.findByBoardId(boardId, pageable);
        return commentMapper.commentListToResponseDtoList(comments);
    }
}
