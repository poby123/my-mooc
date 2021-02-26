package com.mooc.moocServer.service;

import com.mooc.moocServer.domain.Board;
import com.mooc.moocServer.domain.Comment;
import com.mooc.moocServer.domain.Member;
import com.mooc.moocServer.domain.Organization;
import com.mooc.moocServer.repository.MemberRepository;
import com.mooc.moocServer.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final OrganizationRepository organizationRepository;

    @Transactional
    public void addMember(String id, String password, String organizationId){
        Organization organization = organizationRepository.findOne(organizationId);
        Member member = Member.createMember(id, password, organization);
        memberRepository.save(member);
    }

    public List<Board> getMemberBoards(String memberId){
        Member member = memberRepository.findOne(memberId);
        return member.getBoards();
    }

    public List<Comment> getMemberComments(String memberId){
        Member member = memberRepository.findOne(memberId);
        return member.getComments();
    }
}