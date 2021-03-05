package com.mooc.moocServer.service;

import com.mooc.moocServer.entity.Member;
import com.mooc.moocServer.entity.Organization;
import com.mooc.moocServer.repository.MemberRepository;
import com.mooc.moocServer.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final OrganizationRepository organizationRepository;

    @Transactional
    public void addMember(String id, String password, String organizationId) {
        Organization organization = organizationRepository.findOne(organizationId);
        Member member = Member.createMember(id, password, organization);
        organization.addMember(member);
        memberRepository.save(member);
    }

    public Member getMember(String memberId) {
        return memberRepository.findOne(memberId);
    }
}
