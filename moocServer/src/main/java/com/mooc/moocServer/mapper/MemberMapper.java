package com.mooc.moocServer.mapper;

import com.mooc.moocServer.dto.MemberDto;
import com.mooc.moocServer.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberMapper {

    // Member -> MemberSimpleResponse
    public MemberDto.SimpleResponse memberToMemberSimpleResponse(Member member){
        MemberDto.SimpleResponse retValue = new MemberDto.SimpleResponse();

        retValue.setId(member.getId());
        retValue.setImage(member.getImage());
        retValue.setRole(member.getRole());

        return retValue;
    }

    // Member -> MemberResponse
    public MemberDto.Response memberToMemberResponse(Member member){
        MemberDto.Response retValue = new MemberDto.Response();

        retValue.setId(member.getId());
        retValue.setImage(member.getImage());
        retValue.setRole(member.getRole());
        retValue.setOrganization(member.getOrganization());
        retValue.setBoards(member.getBoards());
        retValue.setComments(member.getComments());

        return retValue;
    }

    // List<Member> -> List<MemberSimpleResponse>
    public List<MemberDto.SimpleResponse> memberListToMemberListResponse(List<Member> memberList){
        List<MemberDto.SimpleResponse> retList = new ArrayList<>(memberList.size());

        for(Member m : memberList){
            retList.add(memberToMemberSimpleResponse(m));
        }

        return retList;
    }
}
