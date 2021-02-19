package com.mooc.service;

import java.util.List;

import com.mooc.entity.Member;
import com.mooc.entity.Organization;

public interface MemberService {
	void add(Member member);

	void delete(String id);
	
	Member findOne(String id);
	
	List<Member> findByOrganization(Organization organization);
}
