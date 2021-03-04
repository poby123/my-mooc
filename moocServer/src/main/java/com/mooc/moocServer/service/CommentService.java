package com.mooc.moocServer.service;

import com.mooc.moocServer.domain.Board;
import com.mooc.moocServer.domain.Comment;
import com.mooc.moocServer.domain.Member;
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

    @Transactional
    public Long addComment(String memberId, Long boardId, String content) {
        Member writer = memberRepository.findOne(memberId);
        Board board = boardRepository.findOne(boardId);
        Comment comment = Comment.createComment(writer, board, content);

        writer.addComment(comment);
        board.addComment(comment);
        commentRepository.save(comment);

        return comment.getId();
    }

    public Comment getComment(Long id){
        return commentRepository.findOne(id);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
