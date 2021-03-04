package com.mooc.moocServer.service;

import com.mooc.moocServer.domain.Comment;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Slf4j
public class CommentServiceTest {
    @Autowired
    BoardService boardService;

    @Autowired
    MemberService memberService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    CommentService commentService;

    String organizationId = "or1";
    String categoryName = "cate1";
    String memberId = "mem1";

    @Test
    public void addComment() {
        //given
        Long boardId = create();

        //when
        Long commentId = commentService.addComment(memberId, boardId, "This is comment content");
        Comment comment = commentService.getComment(commentId);

        //then
        assertEquals(commentId, comment.getId());
        log.info("The comment content is : " + comment.getContent());
    }

    public Long create() {
        organizationService.addOrganization(organizationId);
        memberService.addMember(memberId, memberId, organizationId);
        Long categoryId = categoryService.addCategory(categoryName, organizationId);
        return boardService.addBoard(memberId, categoryId, "board content");
    }
}