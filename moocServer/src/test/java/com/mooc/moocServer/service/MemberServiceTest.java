//package com.mooc.moocServer.service;
//
//import com.mooc.moocServer.entity.Member;
//import com.mooc.moocServer.entity.Organization;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class MemberServiceTest {
//
//    @Autowired
//    MemberService memberService;
//
//    @Autowired
//    OrganizationService organizationService;
//
//    @Test
//    public void addAndGetMember() {
//        //given
//        String organizationId = "or1";
//        organizationService.addOrganization(organizationId);
//        Organization organization = organizationService.getOrganization(organizationId);
//
//        //when
//        String memberId = "member1";
//        memberService.addMember(memberId, "member1password", organizationId);
//        Member member = memberService.getMember(memberId);
//
//        //then
//        assertEquals(memberId, member.getId());
//        List<Member> members = organization.getMembers();
//        for (Member m : members) {
//            assertEquals(memberId, m.getId());
//        }
//    }
//}