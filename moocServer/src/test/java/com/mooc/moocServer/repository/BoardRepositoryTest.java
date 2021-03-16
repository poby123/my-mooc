//package com.mooc.moocServer.repository;
//
//import com.mooc.moocServer.entity.Board;
//import com.mooc.moocServer.entity.Category;
//import com.mooc.moocServer.entity.Member;
//import com.mooc.moocServer.entity.Organization;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//@Slf4j
//public class BoardRepositoryTest {
//
//    @Autowired
//    OrganizationRepository organizationRepository;
//
//    @Autowired
//    CategoryRepository categoryRepository;
//
//    @Autowired
//    BoardRepository boardRepository;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Before
//    public void setup() {
//        Organization organization = new Organization("test-organization-id");
//        organization.addCategory(Category.createCategory("name1", organization));
//        organization.addCategory(Category.createCategory("name2", organization));
//
//        organizationRepository.save(organization);
//        Organization one = organizationRepository.findOne("test-organization-id");
//        assertEquals(one.getId(), "test-organization-id");
//        for (Category c : one.getCategories()) {
//            log.info(c.getId() + " " + c.getName());
//        }
//
//        Member member = Member.createMember("test-member-id", "test-password");
//        memberRepository.save(member);
//
//        Category category = categoryRepository.findOne(1L);
//        category.addBoard(Board.createBoard(member, category, "board-content1"));
//        category.addBoard(Board.createBoard(member, category, "board-content2"));
//        category.addBoard(Board.createBoard(member, category, "board-content3"));
//    }
//
//    @Test
//    public void findBoardByCategoryId() {
//        List<Board> boards = boardRepository.findByCategoryId(1L, null);
//        for (Board board : boards) {
//            log.info("content : " + board.getContent());
//        }
//    }
//}
