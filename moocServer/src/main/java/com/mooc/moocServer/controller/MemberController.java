package com.mooc.moocServer.controller;

import com.mooc.moocServer.dto.MemberDto;
import com.mooc.moocServer.entity.Member;
import com.mooc.moocServer.entity.Organization;
import com.mooc.moocServer.service.MemberService;
import com.mooc.moocServer.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final OrganizationService organizationService;
    private final MemberService memberService;

    @GetMapping("/member")
    public List<MemberDto> getMemberList(@RequestParam("organization") String organizationId) {
        Organization organization = organizationService.getOrganization(organizationId);
        List<Member> members = organization.getMembers();
        List<MemberDto> dto = new ArrayList<>();
        for (Member m : members) {
            dto.add(MemberDto.entityToDto(m));
        }
        return dto;
    }

    @GetMapping("/member/{memberId}")
    public MemberDto getMember(@PathVariable("memberId") String memberId) {
        Member member = memberService.getMember(memberId);
        return MemberDto.entityToDto(member);
    }

    @PostMapping("/member")
    public ResponseEntity<?> addMember(@RequestBody Map<String, String> m) {
        memberService.addMember(m.get("id"), m.get("password"));
        Member member = memberService.getMember(m.get("id"));
        return ResponseEntity.status(HttpStatus.CREATED).body(MemberDto.entityToDto(member));
    }

    @PutMapping("/member")
    public MemberDto updateMember(@RequestParam("member") String memberId, @RequestParam("organization") String organizationId) {
        memberService.setOrganization(memberId, organizationId);
        Member member = memberService.getMember(memberId);
        return MemberDto.entityToDto(member);
    }
}
