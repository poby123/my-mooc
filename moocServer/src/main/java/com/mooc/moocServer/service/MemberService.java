package com.mooc.moocServer.service;

import com.mooc.moocServer.dto.MemberDto;
import com.mooc.moocServer.entity.Member;
import com.mooc.moocServer.entity.Organization;
import com.mooc.moocServer.mapper.MemberMapper;
import com.mooc.moocServer.repository.MemberRepository;
import com.mooc.moocServer.repository.OrganizationRepository;
import lombok.NonNull;
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
    private final MemberMapper memberMapper = new MemberMapper();

    @Transactional
    public MemberDto.SimpleResponse addMember(String id, String password) {
        Member member = Member.createMember(id, password);
        memberRepository.save(member);
        return memberMapper.memberToMemberSimpleResponse(member);
    }

    @Transactional
    public MemberDto.Response setOrganization(String id, String organizationId) {
        Member member = memberRepository.findOne(id);
        Organization currentOrganization = member.getOrganization();
        if (currentOrganization != null) {
            currentOrganization.getMembers().remove(member);
        }
        Organization o = organizationRepository.findOne(organizationId);
        member.setOrganization(o);
        o.addMember(member);

        return memberMapper.memberToMemberResponse(member);
    }

    public MemberDto.Response getMember(String memberId) {
        Member member = memberRepository.findOne(memberId);
        return memberMapper.memberToMemberResponse(member);
    }

    public List<MemberDto.SimpleResponse> getMemberList(@NonNull String organizationId) {
        Organization o = organizationRepository.findOne(organizationId);
        List<Member> members = o.getMembers();
        return memberMapper.memberListToMemberListResponse(members);
    }
}
