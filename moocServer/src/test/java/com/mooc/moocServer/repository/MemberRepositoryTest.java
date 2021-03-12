package com.mooc.moocServer.repository;

import com.mooc.moocServer.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    public void 회원가입테스트(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("test-password");
        Member member = Member.createMember("test-member-id", password);
        memberRepository.save(member);
        assertEquals(member.getId(), "test-member-id");
        log.info("멤버 비밀번호 : " + member.getPassword());

        boolean result = passwordEncoder.matches(member.getPassword(), "test-password");
        assertEquals(true, result);
    }
}
