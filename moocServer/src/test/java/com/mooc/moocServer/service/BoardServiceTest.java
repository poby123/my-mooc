//package com.mooc.moocServer.service;
//
//import com.mooc.moocServer.entity.Board;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class BoardServiceTest {
//
//    @Autowired
//    BoardService boardService;
//
//    @Autowired
//    MemberService memberService;
//
//    @Autowired
//    CategoryService categoryService;
//
//    @Autowired
//    OrganizationService organizationService;
//
//    String organizationId = "or1";
//    String categoryName = "cate1";
//    String memberId = "mem1";
//
//    @Test
//    public void addBoard() {
//        //given
//        Long categoryId = create();
//
//        //when
//        Long boardId = boardService.addBoard(memberId, categoryId, "This is test content");
//
//        //then
//        Board board = boardService.getBoard(boardId);
//        assertEquals(board.getContent(), "This is test content");
//    }
//
//    @Test
//    public void getAllBoards() {
//    }
//
//
//    public Long create() {
//        organizationService.addOrganization(organizationId);
//        memberService.addMember(memberId, memberId, organizationId);
//        return categoryService.addCategory(categoryName, organizationId);
//    }
//}