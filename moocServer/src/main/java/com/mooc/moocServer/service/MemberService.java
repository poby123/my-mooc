package com.mooc.moocServer.service;

import com.mooc.moocServer.config.security.JwtTokenProvider;
import com.mooc.moocServer.dto.MemberDto;
import com.mooc.moocServer.entity.Member;
import com.mooc.moocServer.entity.Organization;
import com.mooc.moocServer.mapper.MemberMapper;
import com.mooc.moocServer.repository.MemberRepository;
import com.mooc.moocServer.repository.OrganizationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final OrganizationRepository organizationRepository;
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberDto.SimpleResponse addMember(@NonNull String id, @NonNull String password) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Member member = Member.createMember(id, passwordEncoder.encode(password));
        memberRepository.save(member);
        return memberMapper.memberToMemberSimpleResponse(member);
    }

    @Transactional
    public MemberDto.Response setOrganization(@NonNull String id, @NonNull String organizationId) throws NullPointerException{
        // 대상 멤버 조회
        Member member = memberRepository.findOne(id);
        if(member == null){
            throw new NullPointerException("존재하지 않는 멤버를 수정할 없습니다.");
        }

        // 업데이트하려는 조직 조회
        Organization o = organizationRepository.findOne(organizationId);
        if(o == null){
            throw new NullPointerException("수정하려는 조직은 존재하지 않는 조직입니다.");
        }

        // 기존 조직 조회
        Organization currentOrganization = member.getOrganization();
        if (currentOrganization != null) {
            currentOrganization.getMembers().remove(member);
        }

        member.setOrganization(o);
        o.addMember(member);

        return memberMapper.memberToMemberResponse(member);
    }

    public MemberDto.Response getMember(String memberId) throws NullPointerException {
        Member member = memberRepository.findOne(memberId);

        if(member == null){
            throw new NullPointerException("존재하지 않는 멤버입니다.");
        }

        return memberMapper.memberToMemberResponse(member);
    }

    public List<MemberDto.SimpleResponse> getMemberList(@NonNull String organizationId) throws NullPointerException{
        // 조직 조회
        Organization o = organizationRepository.findOne(organizationId);
        if(o == null){
            throw new NullPointerException("존재하지 않는 조직입니다.");
        }

        // 멤버 목록을 응답형태로 변환.
        List<Member> members = o.getMembers();
        return memberMapper.memberListToMemberSimpleResponseList(members);
    }

    public Member signIn(MemberDto.SignInRequest req) throws IllegalAccessException{
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Member member = memberRepository.findOne(req.getId());
        if (!passwordEncoder.matches(req.getPassword(), member.getPassword())){
            throw new IllegalAccessException("로그인에 실패했습니다");
        }
        return member;
    }

    @Override
    public Member loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberRepository.findOne(id);
        if(member == null){
            throw new UsernameNotFoundException(id + " is not exist");
        }
        return member;
    }
}
