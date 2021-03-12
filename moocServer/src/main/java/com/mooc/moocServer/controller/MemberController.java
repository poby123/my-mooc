package com.mooc.moocServer.controller;

import com.mooc.moocServer.config.security.JwtTokenProvider;
import com.mooc.moocServer.dto.MemberDto;
import com.mooc.moocServer.entity.Member;
import com.mooc.moocServer.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/member")
    public ResponseEntity<?> getMemberList(@RequestParam("organization") String organizationId) {
        List<MemberDto.SimpleResponse> res = memberService.getMemberList(organizationId);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<?> getMember(@PathVariable("memberId") String memberId) {
        MemberDto.Response res = memberService.getMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PutMapping("/member")
    public ResponseEntity<?> updateMember(@RequestParam("member") String memberId, @RequestParam("organization") String organizationId) {
        MemberDto.Response res = memberService.setOrganization(memberId, organizationId);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody MemberDto.SignInRequest signInRequest) throws IllegalAccessException{
        Member member = memberService.signIn(signInRequest);
        MemberDto.SignInResponse res = new MemberDto.SignInResponse(jwtTokenProvider.createToken(member.getId(), member.getRoles()));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/signout")
    public ResponseEntity<?> signOut(HttpServletRequest req, HttpServletResponse res){
        new SecurityContextLogoutHandler().logout(req, res, SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> addMember(@RequestBody MemberDto.SignupRequest req) {
        MemberDto.SimpleResponse res = memberService.addMember(req.getId(), req.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}
