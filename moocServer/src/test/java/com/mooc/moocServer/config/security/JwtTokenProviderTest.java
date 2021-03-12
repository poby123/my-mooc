package com.mooc.moocServer.config.security;

import com.mooc.moocServer.dto.MemberDto;
import com.mooc.moocServer.entity.Member;
import com.mooc.moocServer.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@Transactional
public class JwtTokenProviderTest {
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    MemberService memberService;

    String token;
    String id = "test-user-id";
    String password = "test-password";

    @Before
    public void 토큰생성() throws Exception {
        memberService.addMember(id, password);
        Member member = memberService.signIn(new MemberDto.SignInRequest(id, password));
        MemberDto.SignInResponse res = new MemberDto.SignInResponse(jwtTokenProvider.createToken(member.getId(), member.getRoles()));
        token = res.getResult();
    }

    @Test
    public void 토큰으로부터추출() {
        // id 추출
        String userPk = jwtTokenProvider.getUserPk(token);
        assertEquals("저장한 아이디와 jwt의 아이디를 확인합니다", id, userPk);

        // 권한 추출
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority auth : authorities) {
            String authority = auth.getAuthority();
            log.info("authority : " + authority);
        }
    }
}