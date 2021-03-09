package com.mooc.moocServer.controller;

import com.mooc.moocServer.dto.MemberDto;
import com.mooc.moocServer.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

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

    @PostMapping("/member")
    public ResponseEntity<?> addMember(@RequestBody MemberDto.SignupRequest req) {
        MemberDto.SimpleResponse res = memberService.addMember(req.getId(), req.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/member")
    public ResponseEntity<?> updateMember(@RequestParam("member") String memberId, @RequestParam("organization") String organizationId) {
        MemberDto.Response res = memberService.setOrganization(memberId, organizationId);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
